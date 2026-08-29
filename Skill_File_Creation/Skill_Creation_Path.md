#Prompt:
Tabler view with all tyoe of AI agents github copilot Claude Code cursor windsurf kilo kiro everything, whichever I am using you need to give me exact path where I should put the skill folder so that the agent is able tp read it for open code as well as for command code.

Yes. If your goal is one reusable SKILL.md that can be shared across GitHub Copilot, Claude Code, Cursor, Windsurf, Kilo, Kiro, OpenCode, and Command Code, there is now a very good common denominator: the Agent Skills standard.

The important part is that you do not need to maintain a completely different skill for every agent.

The master recommendation

For maximum interoperability, put your skills here in your repository:

your-project/
├── .agents/
│   └── skills/
│       └── my-skill/
│           ├── SKILL.md
│           ├── references/
│           ├── scripts/
│           └── assets/
│
├── .claude/
│   └── skills/          # optional compatibility copy
├── .cursor/
│   └── skills/          # optional compatibility copy
├── .windsurf/
│   └── skills/          # optional compatibility copy
├── .kiro/
│   └── skills/          # optional compatibility copy
├── .kilo/
│   └── skills/          # optional compatibility copy
├── .opencode/
│   └── skills/          # optional compatibility copy
└── .commandcode/
    └── skills/          # optional compatibility copy

But there is an even better approach: use

.agents/skills/

as your canonical/shared skill location.

A number of these agents explicitly support .agents/skills/ compatibility. Cursor, OpenCode, Command Code, Windsurf and Kilo all document compatibility with .agents/skills/; GitHub Copilot also supports it.

Complete AI Agent → Skill Folder Matrix
AI Agent	Project-level skill path	Global skill path	Reads .agents/skills?	Common SKILL.md?
GitHub Copilot	.github/skills/<skill>/SKILL.md	~/.copilot/skills/<skill>/SKILL.md	✅	✅
Claude Code	.claude/skills/<skill>/SKILL.md	~/.claude/skills/<skill>/SKILL.md	❌ officially listed separately	✅
Cursor	.cursor/skills/<skill>/SKILL.md	~/.cursor/skills/<skill>/SKILL.md	✅	✅
Windsurf	.windsurf/skills/<skill>/SKILL.md	~/.codeium/windsurf/skills/<skill>/SKILL.md	✅	✅
Kilo Code	.kilo/skills/<skill>/SKILL.md	~/.kilo/skills/<skill>/SKILL.md	✅	✅
Kiro	.kiro/skills/<skill>/SKILL.md	~/.kiro/skills/<skill>/SKILL.md	⚠️ not needed	✅
OpenCode	.opencode/skills/<skill>/SKILL.md	~/.config/opencode/skills/<skill>/SKILL.md	✅	✅
Command Code	.commandcode/skills/<skill>/SKILL.md	~/.commandcode/skills/<skill>/SKILL.md	✅	✅
The important distinction

SKILL.md is the standard.

The folder in which you put it determines which agents discover it automatically.

1. GitHub Copilot
Project
.github/skills/my-skill/SKILL.md

GitHub Copilot also supports:

.agents/skills/my-skill/SKILL.md
.claude/skills/my-skill/SKILL.md
Global
~/.copilot/skills/my-skill/SKILL.md

or:

~/.agents/skills/my-skill/SKILL.md

GitHub explicitly documents .github/skills, .claude/skills, .agents/skills, ~/.copilot/skills, and ~/.agents/skills.

2. Claude Code
Project
.claude/skills/my-skill/SKILL.md
Global
~/.claude/skills/my-skill/SKILL.md

Example:

~/.claude/
└── skills/
    └── test-case-generation/
        ├── SKILL.md
        ├── references/
        └── scripts/

Claude Code's official documentation uses exactly these locations.

3. Cursor
Project
.cursor/skills/my-skill/SKILL.md

Cursor also supports:

.agents/skills/my-skill/SKILL.md
.claude/skills/my-skill/SKILL.md
.codex/skills/my-skill/SKILL.md
Global
~/.cursor/skills/my-skill/SKILL.md

or:

~/.agents/skills/my-skill/SKILL.md

Cursor explicitly documents .agents/skills and .cursor/skills as project/global locations.

4. Windsurf
Project
.windsurf/skills/my-skill/SKILL.md
Global
~/.codeium/windsurf/skills/my-skill/SKILL.md

Windsurf also discovers:

.agents/skills/
~/.agents/skills/

and, when Claude Code configuration reading is enabled:

.claude/skills/
~/.claude/skills/

So Windsurf is particularly friendly to the shared .agents approach.

5. Kilo Code
Project
.kilo/skills/my-skill/SKILL.md
Global
~/.kilo/skills/my-skill/SKILL.md

Kilo additionally supports:

.agents/skills/
.claude/skills/

The official Kilo documentation explicitly describes .agents/skills as an interoperability location.

6. Kiro
Project
.kiro/skills/my-skill/SKILL.md
Global
~/.kiro/skills/my-skill/SKILL.md

Kiro's native skill locations are .kiro/skills and ~/.kiro/skills.

For Kiro, I would not rely on .agents/skills as the primary location. Use .kiro/skills if Kiro is important to your workflow.

7. OpenCode

This is particularly interesting because OpenCode has broad compatibility.

Project
.opencode/skills/my-skill/SKILL.md

But OpenCode also searches:

.claude/skills/
.agents/skills/
Global
~/.config/opencode/skills/

and compatibility:

~/.claude/skills/
~/.agents/skills/

OpenCode's current documentation explicitly lists all of these locations.

So OpenCode is one of the reasons I recommend .agents/skills as the shared repository location.

8. Command Code

This is the one you specifically asked about earlier.

Project
.commandcode/skills/my-skill/SKILL.md
Global
~/.commandcode/skills/my-skill/SKILL.md

But Command Code also automatically discovers:

.agents/skills/

and:

~/.agents/skills/

Officially:

.commandcode/skills/
.agents/skills/
~/.commandcode/skills/
~/.agents/skills/

are supported.

This means your Command Code setup can be extremely simple
my-project/
└── .agents/
    └── skills/
        └── test-case-generation/
            └── SKILL.md

Command Code will discover it.

⭐ What I recommend for YOU

Since you are using multiple AI coding agents, don't create eight independent copies unless necessary.

Use this architecture:

my-project/
│
├── .agents/
│   └── skills/
│       │
│       ├── anti-hallucination/
│       │   ├── SKILL.md
│       │   ├── references/
│       │   │   └── rules.md
│       │   └── examples/
│       │
│       ├── test-case-generation/
│       │   ├── SKILL.md
│       │   ├── references/
│       │   └── templates/
│       │
│       ├── jira/
│       │   └── SKILL.md
│       │
│       └── ricepot-method/
│           └── SKILL.md
│
├── .github/
│   └── ...
│
├── src/
├── tests/
└── README.md

Then your agents can consume the same skill package.

Why .agents/skills is the best common location

The key thing is that you're not really trying to optimize for a particular AI product.

You're trying to create:

Agent-independent engineering knowledge.

For example:

.agents/skills/test-case-generation/SKILL.md

can contain your complete testing methodology.

Then:

Claude Code

.claude/skills/

Cursor

.cursor/skills/

Windsurf

.windsurf/skills/

Kilo

.kilo/skills/

etc.

can either use the shared .agents/skills location where supported, or have a compatibility copy/symlink.

⚠️ One important exception: Claude Code

If you want maximum certainty with Claude Code, use:

.claude/skills/

Claude officially documents .claude/skills rather than treating .agents/skills as its primary native directory.

So if Claude Code is one of your major tools, I would use:

.claude/skills/

as the Claude-native location.

My preferred architecture

If I were setting up your machine, I'd use:

                    ┌──────────────────────┐
                    │  MASTER SKILLS       │
                    │                      │
                    │  .agents/skills/     │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
        Cursor           Windsurf          Command Code
        .agents          .agents            .agents
              │                │                │
              └────────────────┼────────────────┘
                               │
                               ▼
                         OpenCode
                         .agents
                              
         Claude Code ─────── .claude/skills
         Kiro ────────────── .kiro/skills
         Kilo ────────────── .kilo/skills
         Copilot ─────────── .agents/skills

And for global personal skills:

~/.agents/skills/

is an excellent shared location for agents that support it.

The actual SKILL.md

Every skill should look approximately like this:

.agents/
└── skills/
    └── test-case-generation/
        ├── SKILL.md
        ├── references/
        │   ├── anti-hallucination.md
        │   └── test-strategy.md
        ├── templates/
        │   └── test-case-template.md
        └── scripts/
            └── validate-tests.py

And:

---
name: test-case-generation
description: Generate comprehensive software test cases from requirements while strictly following anti-hallucination rules, identifying missing requirements, edge cases, negative scenarios, security cases, accessibility cases, and critical coverage gaps.
---

# Test Case Generation

## Purpose

Generate high-quality test cases based only on verified requirements and observable application behavior.

## Mandatory rules

1. Never invent functionality.
2. Never assume undocumented behavior.
3. Separate verified behavior from assumptions.
4. Identify missing requirements explicitly.
5. Cover positive, negative, boundary, error, security, accessibility,
   compatibility, usability, and recovery scenarios.

## Workflow

1. Read the requirements.
2. Identify explicitly supported functionality.
3. Identify ambiguity.
4. Build the functional coverage matrix.
5. Generate test cases.
6. Perform a critical-gap review.
7. Report missing scenarios.

This structure follows the Agent Skills pattern used across these tools.

If you want ONE folder that works with the most agents

Use:

.agents/skills/
Project
PROJECT_ROOT/
└── .agents/
    └── skills/
        └── YOUR-SKILL/
            └── SKILL.md
Global
~/.agents/skills/
└── YOUR-SKILL/
    └── SKILL.md

This is the location I would choose for your shared AI-agent skills.

Then use native folders only when an agent doesn't reliably consume .agents/skills—most notably Kiro and, for maximum Claude compatibility, Claude Code.

Final cheat sheet
Agent	Put skill here
GitHub Copilot	.agents/skills/X/SKILL.md or .github/skills/X/SKILL.md
Claude Code	.claude/skills/X/SKILL.md
Cursor	.agents/skills/X/SKILL.md
Windsurf	.agents/skills/X/SKILL.md
Kilo	.agents/skills/X/SKILL.md
Kiro	.kiro/skills/X/SKILL.md
OpenCode	.agents/skills/X/SKILL.md
Command Code	.agents/skills/X/SKILL.md
Global
Agent	Global
Copilot	~/.agents/skills/
Claude	~/.claude/skills/
Cursor	~/.agents/skills/
Windsurf	~/.agents/skills/
Kilo	~/.agents/skills/
Kiro	~/.kiro/skills/
OpenCode	~/.agents/skills/
Command Code	~/.agents/skills/

So for your use case, I would make .agents/skills/ the master repository and SKILL.md the single source of truth.

If by “Open Code” you mean OpenCode and by “Command Code” you mean Command Code, then yes—the two are particularly easy to support with the same .agents/skills directory.