# JabberPoint Coding Conventions

## 1. Naming

| Element | Convention | Example |
|--------|------------|--------|
| **Classes, interfaces** | PascalCase | `SlideViewerComponent`, `XMLAccessor` |
| **Methods, variables** | camelCase | `getCurrentSlide()`, `currentSlideNumber` |
| **Constants** | UPPER_SNAKE_CASE | `JABVERSION`, `DEFAULT_EXTENSION` |
| **Packages** | lowercase, no underscores | `com.jabberpoint.view` (if you introduce packages) |

## 2. Formatting

- **Indentation:** 4 spaces (no tabs).
- **Line length:** Prefer &lt; 100 characters; break lines sensibly (e.g. after a comma or before an operator).
- **Braces:** Opening brace on same line as declaration; closing on its own line.
  ```java
  if (condition) {
      doSomething();
  }
  ```
- **Blank lines:** One between methods; optional between logical blocks inside a method.

## 3. File and Class Layout

- One top-level **public** class per file; filename = class name (e.g. `Presentation.java`).
- Order inside a class: fields → constructors → public methods → private/package methods (or: static → instance).

## 4. Comments and Javadoc

- Use **Javadoc** for every **public** class and every **public** and **protected** method (brief description; `@param` and `@return` where it helps).
- Use short **line comments** for non-obvious logic; avoid commenting the obvious.

## 5. Visibility and Design

- Prefer **minimum visibility**: `private` by default; `protected` only when subclassing needs it; `public` only for the API you want to expose.
- Avoid **magic numbers**: use named constants (or enums) for values that have a meaning (e.g. slide dimensions, style levels).

## 6. Style and Safety

- Use **`@Override`** when implementing or overriding methods.
- Prefer **meaningful names** over abbreviations, except very common ones (e.g. `max`, `i`, `id`).
- Keep methods **short** where possible; if a method does more than one clear thing, consider splitting it.

## 7. Existing Codebase

- New code should follow these conventions.
- When touching existing code, apply the same rules in the changed lines; full rewrites for style alone are not required.
