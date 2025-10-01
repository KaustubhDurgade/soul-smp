#!/usr/bin/env python3
"""Minimal validator implementing a subset of validation-rules.md.

Checks:
  * Unique IDs
  * Namespace prefix correctness
  * Required id/name presence
  * Conflict tags exist in conflicts matrix
  * Lifesteal, silence, freeze duration caps (simple heuristic)
  * Duplicate conflict tags
Outputs JSON lines for any violations.

Exit code 1 if any error-level violation.
"""
from __future__ import annotations
import yaml, json, pathlib, sys, re
from typing import Dict, Any, List, Set

ROOT = pathlib.Path(__file__).resolve().parent.parent
DATA_DIR = ROOT / 'plans' / 'registry' / 'data'
CONFLICT_FILE = ROOT / 'plans' / 'registry' / 'conflicts.md'

ID_PREFIXES = ('contract.', 'event.', 'mythic.', 'item.', 'resonance.')
ERROR = 'error'
WARN = 'warn'

def load_conflict_tags() -> Set[str]:
    if not CONFLICT_FILE.exists():
        return set()
    tags = set()
    for line in CONFLICT_FILE.read_text(encoding='utf-8').splitlines():
        if line.startswith('|') and 'Domain' in line:
            # header row skip
            continue
        if line.startswith('|') and 'sustain_leech' in line:
            pass
        if line.startswith('|') and line.count('|') >= 5 and not line.startswith('| Tag '):
            parts = [p.strip() for p in line.strip('|').split('|')]
            if parts and parts[0] != 'Tag' and parts[0]:
                tags.add(parts[0])
    return tags

def yaml_entries() -> List[pathlib.Path]:
    if not DATA_DIR.exists():
        return []
    return list(DATA_DIR.glob('*.yaml'))

def parse_yaml(path: pathlib.Path) -> Dict[str, Any] | None:
    try:
        content = path.read_text(encoding='utf-8')
        # Strip initial generated comments
        doc = []
        for line in content.splitlines():
            if line.startswith('#'):
                continue
            doc.append(line)
        data = yaml.safe_load('\n'.join(doc))
        if isinstance(data, dict):
            return data
    except Exception as e:
        emit(path.name, 'STRUCT_PARSE', ERROR, f'YAML parse error: {e}')
    return None

violations: List[Dict[str,str]] = []

def emit(entry_id: str, rule: str, severity: str, msg: str):
    violations.append({'entry': entry_id, 'rule': rule, 'severity': severity, 'message': msg})

def check_caps(entry_id: str, data: Dict[str, Any]):
    text = json.dumps(data)  # crude string search
    # Lifesteal cap
    lifesteal_values = re.findall(r'life\w*steal\w*":\s*([0-9.]+)', text)
    for val in lifesteal_values:
        try:
            f = float(val)
            if f > 0.40:
                emit(entry_id, 'BAL_LIFESTEAL_CAP', ERROR, f'lifesteal value {f} exceeds 0.40 cap')
        except:  # noqa
            pass
    # Silence duration
    if 'silence_duration' in text:
        for m in re.findall(r'silence_duration(?:_add|_seconds|)":\s*([0-9.]+)', text):
            try:
                f = float(m)
                if f > 2.5:
                    emit(entry_id, 'BAL_SILENCE_DURATION', ERROR, f'silence duration {f} > 2.5 cap')
            except:  # noqa
                pass
    # Freeze duration
    if 'freeze_duration' in text:
        for m in re.findall(r'freeze_duration(?:_add|_seconds|)":\s*([0-9.]+)', text):
            try:
                f = float(m)
                if f > 3.5:
                    emit(entry_id, 'BAL_FREEZE_DURATION', ERROR, f'freeze duration {f} > 3.5 cap')
            except:  # noqa
                pass

def main():
    conflict_tags = load_conflict_tags()
    ids: Set[str] = set()
    for path in yaml_entries():
        data = parse_yaml(path)
        if not data:
            continue
        entry_id = data.get('id', f'?{path.name}')
        # ID checks
        if 'id' not in data:
            emit(entry_id, 'STRUCT_REQUIRED_FIELDS', ERROR, 'missing id field')
            continue
        if not entry_id.startswith(ID_PREFIXES):
            emit(entry_id, 'STRUCT_ID_NAMESPACE', ERROR, f'invalid namespace for id {entry_id}')
        if entry_id in ids:
            emit(entry_id, 'STRUCT_UNIQUE_ID', ERROR, f'duplicate id {entry_id}')
        ids.add(entry_id)
        if 'name' not in data:
            emit(entry_id, 'STRUCT_REQUIRED_FIELDS', ERROR, 'missing name field')
        # conflict tags
        tags = data.get('conflict_tags') or []
        if isinstance(tags, list):
            seen = set()
            for t in tags:
                if t not in conflict_tags:
                    emit(entry_id, 'TAG_DEFINED', ERROR, f'undefined conflict tag: {t}')
                if t in seen:
                    emit(entry_id, 'TAG_DUPLICATE', WARN, f'duplicate tag {t}')
                seen.add(t)
        # caps
        check_caps(entry_id, data)
    # Output
    for v in violations:
        print(json.dumps(v))
    if any(v['severity']==ERROR for v in violations):
        sys.exit(1)

if __name__ == '__main__':
    main()
