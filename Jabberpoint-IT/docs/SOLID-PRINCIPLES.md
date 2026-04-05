# SOLID principles in JabberPoint (rubric reference)

Short mapping from each principle to this codebase after refactoring. Use this for reports and reviews.

| Principle | Meaning (brief) | Where it shows in JabberPoint |
|-----------|-----------------|--------------------------------|
| **S** Single responsibility | One reason to change per class | `XMLAccessor` — file I/O only; `Presentation` — slide model; `SlideViewerComponent` — drawing current slide; `StyleManager` — global style table only. |
| **O** Open/closed | Extend without editing stable core | New file formats: add an `Accessor` implementation and extend `AccessorSelector` (Strategy) instead of editing every menu entry. New slide item types: subclass `SlideItem`. |
| **L** Liskov substitution | Subtypes honour the contract | Any `Accessor` can replace another for `loadFile`/`saveFile`; `TextItem` / `BitmapItem` honour `SlideItem` drawing contract. |
| **I** Interface segregation | Small, focused interfaces | `SlideChangeListener` / `SlideChangeSubject` — only slide/title updates, not a fat “UI” interface. |
| **D** Dependency inversion | Depend on abstractions | `Presentation` depends on `SlideChangeListener`, not `SlideViewerComponent`; persistence callers use `Accessor` and `AccessorSelector`, not only `XMLAccessor`. |

This is descriptive documentation; it does not change runtime behaviour.
