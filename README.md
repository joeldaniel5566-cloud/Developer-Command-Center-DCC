# 🖥️ Developer Command Center (DCC)

**A unified desktop workspace for developers.**

Developer Command Center is a desktop application built using Java and web technologies, designed to provide developers with a centralised workspace for analysing and exploring their development projects.

The application combines Java's backend capabilities with an interactive HTML, CSS, and JavaScript interface through JavaFX WebView.

---

## 🚧 Project Status

**Work in Progress (WIP)**

This project is currently under development. The initial dashboard, project directory selection, and project scanning functionality have been implemented. Additional analysis modules are planned for future development.

## 📸 Preview
<img width="1920" height="1080" alt="Screenshot 2026-11-24 173903" src="https://github.com/user-attachments/assets/105a2342-a118-4d63-9744-8f805010f8ff" />


## ✨ Implemented Features

- Interactive dark-themed dashboard.
- Project directory selection using JavaFX DirectoryChooser.
- Project scanning using Java NIO.
- Support for scanning multiple project directories.
- Java and JavaScript communication through JavaFX WebView.
- Dynamic project information handling.

## 🔮 Planned Features

- **Project Analyzer:** Analyse project structure and file statistics.
- **Database Analyzer:** Explore and analyse database structures.
- **Log Analyzer:** View and analyse application logs.
- **System Monitor:** Monitor system-related information.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend development |
| JavaFX | Desktop application framework |
| JavaFX WebView | Displaying the web interface |
| HTML5 | Frontend structure |
| CSS3 | Interface styling |
| JavaScript | Frontend interactions |
| Java NIO | File and directory scanning |

## 🏗️ Architecture

DCC uses a hybrid desktop architecture:

1. JavaFX launches the desktop application.
2. JavaFX WebView loads the HTML interface.
3. JavaConnector exposes Java functionality to JavaScript.
4. ProjectScanner scans the selected directory.
5. Project information is processed and returned to the interface.

## ⚙️ Installation

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Configure the required JavaFX dependencies.
4. Run the main application class.
5. Select a project directory to begin scanning.

**Note:** JavaFX SDK and its required dependencies must be configured before running the application.

## 📂 Project Modules

| Module | Status |
|---|---|
| Dashboard | Implemented |
| Project Directory Selection | Implemented |
| Project Scanner | Implemented |
| Project Analyzer | Planned |
| Database Analyzer | Planned |
| Log Analyzer | Planned |
| System Monitor | Planned |

## 🎯 Project Goals

The long-term goal is to develop a unified developer workspace that simplifies project exploration, analysis, and monitoring through a single desktop application.

## 📌 Disclaimer

This project is an independent development project and is currently under development. Features listed under planned modules have not yet been implemented.

---

**Developer Command Center — Building a smarter workspace for developers.**
