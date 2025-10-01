#!/usr/bin/env python3
"""Convert spec markdown files with YAML frontmatter into registry YAML entries.

Current scope: contracts, events, mythics.
Resonances & items can be added later.

Rules:
  * Reads any markdown under plans/contracts, plans/events, plans/mythics with frontmatter.
  * Writes to plans/registry/data/<id_with_dot_replaced_by_underscore>.yaml
  * Skips if target YAML already exists (unless --force passed).
  * Normalizes key ordering (simple heuristic) and adds generated header comment.
"""
from __future__ import annotations
import argparse
import pathlib
import sys
import yaml
import re
from typing import Dict, Any

ROOT = pathlib.Path(__file__).resolve().parent.parent
SPEC_DIRS = [
    ROOT / 'plans' / 'contracts',
    ROOT / 'plans' / 'events',
    ROOT / 'plans' / 'mythics',
]
OUT_DIR = ROOT / 'plans' / 'registry' / 'data'

FRONTMATTER_PATTERN = re.compile(r'^---\s*$')

KEY_ORDER = [
    'id','name','tier','class','category','mythic_type','activation_mode','acquisition_path','soul_focus',
    'scope','rarity','intensity_tier','cooldown_seconds','active_window_seconds','activation','sacrifice','effects',
    'world_object','transformation','passives','active','scaling','resonance_hooks','conflict_tags','anti_abuse',
    'attunement','visual','logging','status'
]

def extract_frontmatter(text: str) -> Dict[str, Any] | None:
    lines = text.splitlines()
    if not lines or not FRONTMATTER_PATTERN.match(lines[0]):
        return None
    fm_lines = []
    for line in lines[1:]:
        if FRONTMATTER_PATTERN.match(line):
            break
        fm_lines.append(line)
    if not fm_lines:
        return None
    try:
        data = yaml.safe_load('\n'.join(fm_lines))
        if not isinstance(data, dict):
            return None
        return data
    except Exception as e:
        print(f"Failed to parse frontmatter: {e}", file=sys.stderr)
        return None

def normalize_order(data: Dict[str, Any]) -> Dict[str, Any]:
    ordered: Dict[str, Any] = {}
    for k in KEY_ORDER:
        if k in data:
            ordered[k] = data[k]
    # append any remaining keys
    for k in data:
        if k not in ordered:
            ordered[k] = data[k]
    return ordered

def id_to_filename(entry_id: str) -> str:
    return entry_id.replace('.', '_') + '.yaml'

def convert_file(md_path: pathlib.Path, force: bool=False) -> str | None:
    text = md_path.read_text(encoding='utf-8')
    fm = extract_frontmatter(text)
    if not fm or 'id' not in fm:
        return None
    out_name = id_to_filename(fm['id'])
    OUT_DIR.mkdir(parents=True, exist_ok=True)
    out_path = OUT_DIR / out_name
    if out_path.exists() and not force:
        return None
    ordered = normalize_order(fm)
    header = (
        f"# GENERATED FROM: {md_path.relative_to(ROOT)}\n"
        f"# DO NOT EDIT MANUALLY. Update the source spec and re-run convert_specs.py.\n"
    )
    yaml_text = yaml.safe_dump(ordered, sort_keys=False, allow_unicode=True).rstrip() + '\n'
    out_path.write_text(header + yaml_text, encoding='utf-8')
    return out_name

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--force', action='store_true', help='Overwrite existing YAML')
    ap.add_argument('--dry-run', action='store_true')
    args = ap.parse_args()
    generated = []
    for spec_dir in SPEC_DIRS:
        if not spec_dir.exists():
            continue
        for md in spec_dir.glob('*.md'):
            if md.name.lower() == 'readme.md':
                continue
            result = convert_file(md, force=args.force)
            if result:
                generated.append(result)
    if args.dry_run:
        print('[DRY RUN] Would generate:')
    if generated:
        for g in generated:
            print(g)
    else:
        print('No new YAML generated (already up to date).')

if __name__ == '__main__':
    main()
