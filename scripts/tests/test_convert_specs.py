import json
import textwrap

import yaml

from scripts import convert_specs


def test_extract_summary_prefers_summary_section():
    body = textwrap.dedent(
        """
        ## Summary
        Line one.
        Line two.

        ## Mechanics
        Details follow.
        """
    )
    assert convert_specs.extract_summary(body) == "Line one.\nLine two."


def test_extract_summary_fallback_first_paragraph():
    body = textwrap.dedent(
        """
        Intro line one.
        Intro line two.

        ## Mechanics
        Details.
        """
    )
    assert convert_specs.extract_summary(body) == "Intro line one. Intro line two."


def test_convert_file_generates_artifacts(tmp_path, monkeypatch):
    root = tmp_path
    spec_dir = root / "plans" / "contracts"
    spec_dir.mkdir(parents=True)
    registry_dir = root / "plans" / "registry"

    md_path = spec_dir / "contract_sample.md"
    md_path.write_text(
        textwrap.dedent(
            """
            ---
            id: contract.sample
            name: Sample Contract
            tier: 1
            class: SUPPORT
            scope: LOCAL
            status: draft
            ---
            ## Summary
            A short description for tooltips.

            ## Mechanics
            Details about the contract.
            """
        ),
        encoding="utf-8",
    )

    monkeypatch.setattr(convert_specs, "ROOT", root)
    monkeypatch.setattr(convert_specs, "SPEC_DIRS", [spec_dir])
    monkeypatch.setattr(convert_specs, "OUT_DIR", registry_dir / "data")
    monkeypatch.setattr(convert_specs, "TOOLTIP_JSON_DIR", registry_dir / "tooltips" / "json")
    monkeypatch.setattr(convert_specs, "TOOLTIP_MD_DIR", registry_dir / "tooltips" / "md")

    actions = convert_specs.convert_file(md_path, force=True, dry_run=False)

    yaml_path = registry_dir / "data" / "contract_sample.yaml"
    json_path = registry_dir / "tooltips" / "json" / "contract_sample.json"
    md_out_path = registry_dir / "tooltips" / "md" / "contract_sample.md"

    assert yaml_path.exists()
    assert json_path.exists()
    assert md_out_path.exists()

    # YAML payload should include header comment and notes/status defaults
    yaml_lines = [line for line in yaml_path.read_text(encoding="utf-8").splitlines() if not line.startswith("#")]
    yaml_payload = yaml.safe_load("\n".join(yaml_lines))
    assert yaml_payload["id"] == "contract.sample"
    assert yaml_payload["notes"] == []
    assert yaml_payload["status"] == "draft"

    # JSON tooltip contains summary text
    tooltip = json.loads(json_path.read_text(encoding="utf-8"))
    assert tooltip["summary"].startswith("A short description")
    assert tooltip["source"].endswith("contract_sample.md")

    # Markdown tooltip includes heading and ID
    md_content = md_out_path.read_text(encoding="utf-8")
    assert "# Sample Contract" in md_content
    assert "**ID:** contract.sample" in md_content

    kinds = {kind for kind, _ in actions}
    assert {"yaml", "tooltip_json", "tooltip_md"}.issubset(kinds)