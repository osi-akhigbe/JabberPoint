# JabberPoint UML Diagrams for Astah

This document provides specifications for creating **Class**, **Use Case**, and **Sequence** diagrams of the JabberPoint project in Astah Professional or Astah UML.

---

## 1. Class Diagram

### Method A: Import from Java (Recommended)

Astah can generate the class diagram directly from the Java source code:

1. Open Astah and create a new project
2. Set Java as the modeling language: Select root package → check **[Java]** in bottom pane
3. Go to **Tools → Java → Import Java**
4. Navigate to `Jabberpoint-IT/src/` and select all `.java` files
5. Click OK, ensure correct encoding (UTF-8)
6. When prompted, choose which attributes to import as associations
7. Right-click the imported package → **Auto Create Class Diagram**
8. Use **Alignment → Auto Layout** to arrange the diagram
9. Save as `.asta` (e.g., `Jabberpoint-ClassDiagram.asta`)

**Empty boxes (JFrame, Frame, Rectangle, Graphics, etc.):** After importing from Java, Astah adds the project’s classes plus the JDK types they use or extend (e.g. `JFrame`, `Frame`, `Rectangle`, `Graphics`, `JComponent`, `MenuBar`, `KeyAdapter`). Those JDK types often appear as **empty or nearly empty** boxes because Astah doesn’t have their source. To keep the diagram focused on JabberPoint only, remove those from the diagram: in the diagram editor, select the boxes for `JFrame`, `Frame`, `Rectangle`, `Graphics`, `JComponent`, `MenuBar`, `KeyAdapter`, etc. and delete them from the diagram (or move them to an “External” package and collapse/hide it). The project classes (Presentation, Slide, SlideViewerFrame, etc.) will keep their relationships; you’re only removing the library classes from the view.

### Method B: Manual Class Diagram Specification

If you prefer to draw manually, here is the complete structure:

#### Classes and Relationships

| Class | Superclass | Key Attributes | Key Methods |
|-------|------------|----------------|-------------|
| **JabberPoint** | — | JABVERSION, IOERR, JABERR | main(String[]) |
| **Presentation** | — | showTitle, showList (ArrayList<Slide>), currentSlideNumber, slideChangeListeners; implements **SlideChangeSubject** | addSlideChangeListener(), removeSlideChangeListener(), getSize(), getTitle(), setTitle(), getSlideNumber(), setSlideNumber(), prevSlide(), nextSlide(), clear(), append(), getSlide(), getCurrentSlide(), exit() |
| **Slide** | — | title, items (Vector<SlideItem>) | append(), getTitle(), setTitle(), getSlideItem(), getSlideItems(), getSize(), draw() |
| **SlideItem** | — (abstract) | level | getLevel(), getBoundingBox(), draw() [abstract] |
| **TextItem** | SlideItem | text | getText(), getAttributedString(), getBoundingBox(), draw() |
| **BitmapItem** | SlideItem | bufferedImage, imageName | getName(), getBoundingBox(), draw() |
| **Style** | — | indent, color, font, fontSize, leading | getFont() |
| **StyleManager** | — | (singleton) styles table | getInstance(), getStyle(int) |
| **Accessor** | — (abstract) | — | getDemoAccessor(), loadFile(), saveFile() [abstract] |
| **XMLAccessor** | Accessor | — | loadFile(), saveFile(), loadSlideItem() |
| **AccessorSelector** | — | — | forFilename(String): Accessor |
| **DemoPresentation** | Accessor | — | loadFile(), saveFile() |
| **SlideViewerFrame** | JFrame | — | setupWindow() |
| **SlideViewerComponent** | JComponent | slide, presentation, frame | slideChanged(), update(), paintComponent() |
| **KeyController** | KeyAdapter | presentation | keyPressed() |
| **MenuController** | MenuBar | parent, presentation | mkMenuItem(), action listeners |
| **AboutBox** | — | — | show(Frame) |
| **SlideChangeListener** | — (interface) | — | slideChanged(Presentation, Slide) |
| **SlideChangeSubject** | — (interface) | — | addSlideChangeListener(), removeSlideChangeListener() |

#### Relationships

- **Presentation** ←──1── * Slide (composition: showList)
- **Slide** ←──1── * SlideItem (composition: items)
- **SlideItem** ◇── TextItem (generalization)
- **SlideItem** ◇── BitmapItem (generalization)
- **Accessor** ◇── XMLAccessor (generalization)
- **Accessor** ◇── DemoPresentation (generalization)
- **Presentation** ◇── SlideChangeSubject (realization); **Presentation** o── SlideChangeListener (observers)
- **SlideViewerFrame** ───1── SlideViewerComponent (composition)
- **SlideViewerFrame** ───> Presentation (association); registers **SlideViewerComponent** as **SlideChangeListener**
- **KeyController** ───> Presentation (association)
- **MenuController** ───> Presentation (association)
- **Slide** ───> StyleManager (dependency: getStyle)
- **SlideItem** ───> Style (dependency: draw, getBoundingBox)
- **JabberPoint** ───> Presentation, AccessorSelector, StyleManager, SlideViewerFrame (creation/dependency)
- **MenuController** ───> AccessorSelector (dependency)
- **AccessorSelector** ───> Accessor (returns strategy)
- **XMLAccessor** ───> Slide, TextItem, BitmapItem (creation)

---

## 2. Use Case Diagram

### Actors

- **User** (primary actor) – person viewing or controlling the presentation

### Use Cases

| Use Case | Description |
|----------|-------------|
| **View presentation** | User opens and views the slide show |
| **Navigate next slide** | User advances to the next slide (PgDn, Enter, Down, +) |
| **Navigate previous slide** | User goes back to the previous slide (PgUp, Up, -) |
| **Go to slide** | User jumps to a specific slide by number (View → Go to) |
| **Open presentation** | User loads a presentation from XML file (File → Open) |
| **New presentation** | User clears current presentation and starts fresh (File → New) |
| **Save presentation** | User saves presentation to XML file (File → Save) |
| **Exit application** | User quits JabberPoint (File → Exit or Q key) |
| **View about** | User views application information (Help → About) |

### Relationships

- **View presentation** include **Navigate next slide**
- **View presentation** include **Navigate previous slide**
- **View presentation** extend **Go to slide**
- **View presentation** include **Exit application**
- **User** → (performs) → all use cases above

### Include/Extend

- **View presentation** is the main use case
- **Open presentation** and **New presentation** precede **View presentation**
- **Save presentation** can be performed during **View presentation**

---

## 3. Sequence Diagrams

### 3.1 Sequence: Application Startup and Load Presentation

```
Actor: User
Participants: JabberPoint, StyleManager, Presentation, SlideViewerFrame, SlideViewerComponent, AccessorSelector, Accessor, XMLAccessor/DemoPresentation

User -> JabberPoint: main(argv)
JabberPoint -> StyleManager: getInstance()
JabberPoint -> Presentation: new Presentation()
JabberPoint -> SlideViewerFrame: new SlideViewerFrame(title, presentation)
SlideViewerFrame -> SlideViewerComponent: new SlideViewerComponent(presentation, this)
SlideViewerFrame -> Presentation: addSlideChangeListener(slideViewerComponent)
SlideViewerFrame -> SlideViewerFrame: setupWindow(...)

alt argv.length == 0
    JabberPoint -> Accessor: getDemoAccessor()
    Accessor --> JabberPoint: DemoPresentation
    JabberPoint -> DemoPresentation: loadFile(presentation, "")
    DemoPresentation -> Presentation: setTitle(...)
    DemoPresentation -> Presentation: append(slide) [x3]
else argv.length > 0
    JabberPoint -> AccessorSelector: forFilename(filename)
    AccessorSelector --> JabberPoint: Accessor (XMLAccessor)
    JabberPoint -> XMLAccessor: loadFile(presentation, filename)
    XMLAccessor -> Presentation: setTitle(...)
    loop for each slide
        XMLAccessor -> Slide: new Slide()
        XMLAccessor -> Slide: setTitle(...)
        XMLAccessor -> Presentation: append(slide)
        loop for each item
            XMLAccessor -> Slide: append(TextItem/BitmapItem)
        end
    end
end

JabberPoint -> Presentation: setSlideNumber(0)
Presentation -> SlideViewerComponent: update(presentation, getCurrentSlide())
SlideViewerComponent -> SlideViewerComponent: repaint()
```

### 3.2 Sequence: Navigate to Next Slide (Keyboard)

```
Actor: User
Participants: KeyController, Presentation, SlideViewerComponent, Slide

User -> KeyController: keyPressed(PAGE_DOWN/ENTER/DOWN/+)
KeyController -> Presentation: nextSlide()
Presentation -> Presentation: setSlideNumber(currentSlideNumber + 1)
Presentation -> SlideViewerComponent: slideChanged(this, getCurrentSlide())
SlideViewerComponent -> SlideViewerComponent: update(presentation, slide)
SlideViewerComponent -> SlideViewerComponent: repaint()
SlideViewerComponent -> Slide: draw(g, area, this)
```

### 3.3 Sequence: Open File from Menu

```
Actor: User
Participants: MenuController, Presentation, AccessorSelector, Accessor, XMLAccessor, JOptionPane

User -> MenuController: actionPerformed(Open)
MenuController -> Presentation: clear()
Presentation -> Presentation: clear() [new ArrayList, setSlideNumber(-1)]
MenuController -> AccessorSelector: forFilename("test.xml")
AccessorSelector --> MenuController: Accessor
MenuController -> XMLAccessor: loadFile(presentation, "test.xml")
XMLAccessor -> Presentation: setTitle(...)
loop for each slide in XML
    XMLAccessor -> Slide: new Slide()
    XMLAccessor -> Presentation: append(slide)
    loop for each item
        XMLAccessor -> Slide: append(TextItem/BitmapItem)
    end
end
MenuController -> Presentation: setSlideNumber(0)
MenuController -> parent: repaint()
```

### 3.4 Sequence: Draw Slide (Rendering)

```
Participants: SlideViewerComponent, Slide, StyleManager, Style, TextItem, BitmapItem

SlideViewerComponent -> SlideViewerComponent: paintComponent(g)
SlideViewerComponent -> Presentation: getSlideNumber()
SlideViewerComponent -> Slide: draw(g, area, this)
Slide -> Slide: getScale(area)
Slide -> TextItem: new TextItem(0, getTitle()) [for title]
Slide -> StyleManager: getInstance().getStyle(slideItem.getLevel())
StyleManager --> Slide: Style
Slide -> TextItem: draw(x, y, scale, g, style, view)
Slide -> TextItem: getBoundingBox(...)
loop for each slide item
    Slide -> Slide: getSlideItem(number)
    Slide -> StyleManager: getInstance().getStyle(level)
    Slide -> SlideItem: draw(x, y, scale, g, style, view)
    Slide -> SlideItem: getBoundingBox(...)
end
```

### 3.5 Sequence: Save Presentation

```
Actor: User
Participants: MenuController, AccessorSelector, Accessor, XMLAccessor, Presentation, Slide, SlideItem

User -> MenuController: actionPerformed(Save)
MenuController -> AccessorSelector: forFilename("dump.xml")
AccessorSelector --> MenuController: Accessor
MenuController -> XMLAccessor: saveFile(presentation, "dump.xml")
XMLAccessor -> Presentation: getTitle()
XMLAccessor -> Presentation: getSize()
loop for each slide
    XMLAccessor -> Presentation: getSlide(number)
    XMLAccessor -> Slide: getTitle()
    XMLAccessor -> Slide: getSlideItems()
    loop for each item
        XMLAccessor -> SlideItem: getLevel()
        alt TextItem
            XMLAccessor -> TextItem: getText()
        else BitmapItem
            XMLAccessor -> BitmapItem: getName()
        end
    end
end
XMLAccessor -> FileWriter: write XML to file
```

---

## File Structure for Astah Project

Suggested organization when creating in Astah:

```
JabberPoint Project
├── Class Diagram (from Java import or manual)
├── Use Case Diagram
│   └── Package: JabberPoint Use Cases
│       ├── Actor: User
│       └── Use cases as listed above
└── Sequence Diagrams
    ├── Startup and Load
    ├── Navigate Next Slide
    ├── Open File
    ├── Draw Slide
    └── Save Presentation
```

---

## Quick Reference: Key Flows

| Action | Entry Point | Main Classes |
|--------|-------------|--------------|
| Start app | JabberPoint.main() | JabberPoint, Presentation, SlideViewerFrame |
| Load XML | XMLAccessor.loadFile() | XMLAccessor, Presentation, Slide |
| Load demo | DemoPresentation.loadFile() | DemoPresentation, Presentation, Slide |
| Next slide | KeyController.keyPressed() or MenuController | Presentation.nextSlide() |
| Prev slide | KeyController.keyPressed() or MenuController | Presentation.prevSlide() |
| Open file | MenuController (File→Open) | XMLAccessor.loadFile() |
| Save file | MenuController (File→Save) | XMLAccessor.saveFile() |
| Draw slide | SlideViewerComponent.paintComponent() | Slide.draw(), SlideItem.draw() |
