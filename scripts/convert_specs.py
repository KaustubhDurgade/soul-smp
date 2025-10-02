#!/usr/bin/env python3
"""Convert spec markdown files with YAML frontmatter into registry artifacts.

Current scope: contracts, events, mythics.
Resonances & items can be added later.

Outputs per spec:
  * Registry YAML in ``plans/registry/data``
  * JSON tooltip summary in ``plans/registry/tooltips/json``
  * Markdown tooltip summary in ``plans/registry/tooltips/md``

Rules:
  * Reads markdown recursively under ``plans/contracts``, ``plans/events``, ``plans/mythics``.
  * Writes to ``plans/registry/data/<id_with_dot_replaced_by_underscore>.yaml``.
  * Skips writing artifacts when an up-to-date version already exists (unless ``--force``).
  * Normalizes key ordering (simple heuristic), injects default ``notes``/``status`` fields, and adds generated header comment.
"""
from __future__ import annotations

import argparse
import json
import pathlib
import re
import sys
from typing import Dict, Any, Iterable, List, Tuple

import yaml

ROOT = pathlib.Path(__file__).resolve().parent.parent
SPEC_DIRS = [
    ROOT / 'plans' / 'contracts',
    ROOT / 'plans' / 'events',
    ROOT / 'plans' / 'mythics',
]
OUT_DIR = ROOT / 'plans' / 'registry' / 'data'
TOOLTIP_JSON_DIR = ROOT / 'plans' / 'registry' / 'tooltips' / 'json'
TOOLTIP_MD_DIR = ROOT / 'plans' / 'registry' / 'tooltips' / 'md'

FRONTMATTER_PATTERN = re.compile(r'^---\s*$')
FRONTMATTER_EMBEDDED_COLON = re.compile(
    r'(^[ \t]*[^:\n]+:[ \t]*)([^"\'\n]*\([^:\n]+:[^\n]*\)[^"\'\n]*)',
    re.MULTILINE,
)

KEY_ORDER = [
    'id', 'name', 'tier', 'class', 'category', 'mythic_type', 'activation_mode', 'acquisition_path', 'soul_focus',
    'scope', 'rarity', 'intensity_tier', 'cooldown_seconds', 'active_window_seconds', 'activation', 'sacrifice', 'effects',
    'world_object', 'transformation', 'passives', 'active', 'scaling', 'resonance_hooks', 'conflict_tags', 'anti_abuse',
    'attunement', 'visual', 'logging', 'notes', 'status'
]


def parse_frontmatter(text: str) -> Tuple[Dict[str, Any] | None, str]:
    lines = text.splitlines()
    while lines and not lines[0].strip():
        lines.pop(0)
    if not lines or not FRONTMATTER_PATTERN.match(lines[0]):
        return None, text
    fm_lines: List[str] = []
    body_start: int | None = None
    for idx, line in enumerate(lines[1:], start=1):
        if FRONTMATTER_PATTERN.match(line):
            body_start = idx + 1
            break
        fm_lines.append(line)
    if not fm_lines:
        return None, text
    fm_text = '\n'.join(fm_lines)
    fm_text = FRONTMATTER_EMBEDDED_COLON.sub(lambda m: f"{m.group(1)}\"{m.group(2).strip()}\"", fm_text)
    try:
        data = yaml.safe_load(fm_text)
        if not isinstance(data, dict):
            return None, text
        body = '\n'.join(lines[body_start:]) if body_start is not None else ''
        return data, body
    except Exception as exc:  # pragma: no cover - defensive logging
        print(f"Failed to parse frontmatter: {exc}", file=sys.stderr)
        return None, text


def normalize_order(data: Dict[str, Any]) -> Dict[str, Any]:
    defaults: Dict[str, Any] = dict(data)
    defaults.setdefault('notes', [])
    defaults.setdefault('status', defaults.get('status', 'draft'))
    ordered: Dict[str, Any] = {}
    for key in KEY_ORDER:
        if key in defaults:
            ordered[key] = defaults[key]
    for key in defaults:
        if key not in ordered:
            ordered[key] = defaults[key]
    return ordered


def id_to_filename(entry_id: str, extension: str = '.yaml') -> str:
    base = entry_id.replace('.', '_')
    if extension and not extension.startswith('.'):
        extension = f'.{extension}'
    return base + extension


def extract_summary(body: str) -> str:
    lines = body.splitlines()
    summary: List[str] = []
    capturing = False
    for line in lines:
        if line.strip().startswith('## '):
            if line.strip().lower() == '## summary':
                capturing = True
                continue
            if capturing:
                break
        if capturing:
            summary.append(line.rstrip())
    if summary:
        return '\n'.join(summary).strip()
    paragraph: List[str] = []
    for line in lines:
        stripped = line.strip()
        if not stripped:
            if paragraph:
                break
            continue
        if stripped.startswith('## '):
            if paragraph:
                break
            continue
        paragraph.append(stripped)
    return ' '.join(paragraph).strip()


def write_if_changed(path: pathlib.Path, content: str, dry_run: bool, force: bool = False) -> bool:
    current = path.read_text(encoding='utf-8') if path.exists() else None
    if not force and current == content:
        return False
    if not dry_run:
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(content, encoding='utf-8')
    return True


def build_tooltip_payload(data: Dict[str, Any], summary: str, source: pathlib.Path) -> Dict[str, Any]:
    payload: Dict[str, Any] = {
        'id': data.get('id'),
        'name': data.get('name'),
        'status': data.get('status'),
        'summary': summary,
        'source': str(source),
    }
    if 'tier' in data:
        payload['tier'] = data['tier']
    if 'category' in data:
        payload['category'] = data['category']
    if 'class' in data:
        payload['class'] = data['class']
    if 'conflict_tags' in data:
        payload['conflict_tags'] = data['conflict_tags']
    return payload


def tooltip_markdown(data: Dict[str, Any], summary: str) -> str:
    lines = [
        f"# {data.get('name', data.get('id', 'Unknown'))}",
        '',
        f"- **ID:** {data.get('id', 'n/a')}",
        f"- **Status:** {data.get('status', 'draft')}",
    ]
    if 'tier' in data and data['tier'] is not None:
        lines.append(f"- **Tier:** {data['tier']}")
    category = data.get('category') or data.get('class')
    if category:
        lines.append(f"- **Category:** {category}")
    if summary:
        lines.extend(['', summary])
    return '\n'.join(lines).rstrip() + '\n'


def convert_file(md_path: pathlib.Path, force: bool = False, dry_run: bool = False) -> List[Tuple[str, str]]:
    text = md_path.read_text(encoding='utf-8')
    fm, body = parse_frontmatter(text)
    if not fm or 'id' not in fm:
        return []
    out_name = id_to_filename(fm['id'])
    out_path = OUT_DIR / out_name
    actions: List[Tuple[str, str]] = []

    ordered = normalize_order(fm)
    header = (
        f"# GENERATED FROM: {md_path.relative_to(ROOT)}\n"
        f"# DO NOT EDIT MANUALLY. Update the source spec and re-run convert_specs.py.\n"
    )
    yaml_text = yaml.safe_dump(ordered, sort_keys=False, allow_unicode=True).rstrip() + '\n'
    if write_if_changed(out_path, header + yaml_text, dry_run, force=force):
        actions.append(('yaml', out_name))

    summary = extract_summary(body)
    tooltip_base = fm['id'].replace('.', '_')
    json_path = TOOLTIP_JSON_DIR / (tooltip_base + '.json')
    md_out_path = TOOLTIP_MD_DIR / (tooltip_base + '.md')
    tooltip_payload = build_tooltip_payload(ordered, summary, md_path.relative_to(ROOT))
    json_text = json.dumps(tooltip_payload, indent=2, ensure_ascii=False) + '\n'
    md_text = tooltip_markdown(ordered, summary)

    if write_if_changed(json_path, json_text, dry_run, force=force):
        actions.append(('tooltip_json', json_path.name))
    if write_if_changed(md_out_path, md_text, dry_run, force=force):
        actions.append(('tooltip_md', md_out_path.name))

    return actions


def iter_spec_files(directories: Iterable[pathlib.Path]) -> Iterable[pathlib.Path]:
    for directory in directories:
        if not directory.exists():
            continue
        for md in directory.rglob('*.md'):
            if md.name.lower() == 'readme.md':
                continue
            yield md


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument('--force', action='store_true', help='Overwrite existing artifacts')
    parser.add_argument('--dry-run', action='store_true', help='Only report what would change')
    args = parser.parse_args()

    generated: List[Tuple[str, str]] = []
    for md_file in iter_spec_files(SPEC_DIRS):
        actions = convert_file(md_file, force=args.force, dry_run=args.dry_run)
        generated.extend(actions)

    if args.dry_run:
        print('[DRY RUN] Would generate:')
    if generated:
        for kind, name in generated:
            print(f"{kind}: {name}")
    else:
        print('No new YAML generated (already up to date).')


if __name__ == '__main__':
    main()
