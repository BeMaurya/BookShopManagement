#📚 Book Shop Management System

A simple and efficient book shop management system that supports book inventory, customer records, billing, and reporting. Built for learning and practical database application development.
---
##✨ Features
- 📋 Manage book inventory (Add, Delete, Update, Search)
- 👤 Customer information management
- 💰 Billing and invoice generation
- 📊 View reports of sales
- 🗃️ Simple and intuitive UI/CLI
- 🛠️ Connected to database for persistent storage

---

## 🧰 Tech Stack
- 🖥️ Front-end / UI: (Swing)
- 🧠 Backend: (Java)
- 🗄️ Database: (Oracle SQL)
- ⚡ Tools: (JDBC)

📁 Project Structure
```text
BookShopManagement/
├── src/                  # Java source code
├── lib/                  # External libraries (JDBC drivers)
├── bin/                  # Compiled classes
├── .settings/            # Eclipse settings
├── .classpath
├── .project
│
├── database/
│   ├── sql/
│   │   ├── userSQL.sql        # DB user creation
│   │   └── tableSQL.sql       # Table creation
│   │
│   └── scripts/
│       ├── createUser.bat     # Run userSQL.sql
│       └── createTable.bat    # Run tableSQL.sql
│
├── docs/                 # (Optional) diagrams, report
├── screenshots/          # (Optional) outputs / UI
└── README.md
```

## ⚙️ How to Install
### 🧱 Prerequisites
- 💻 JDK
- 🗄️ Database installed (Oracle)

###📥 Setup Steps
```bash
git clone https://github.com/BeMaurya/BookShopManagement.git
cd BookShopManagement
```

### Configure your database

- Create schema

- Update connection config

- Build/run the project
For Java:
java -jar BookShopManagement.jar

## 🏃 Usage
- Add new book
- View all books
- Search book by ID/Name
- Create customer bill

## 🧠 Why This Project?
Built to demonstrate:
- Database CRUD operations
- Object mapping and persistence
- User-friendly interface
- Practical application of backend + database
- Great for interviews, portfolio, and real-world practice.

## ❤️ Contributions
Contributions are welcome!
Fork the repo → Create a branch → Add feature → Submit PR

<div align="center">
<p>📘 This project is created strictly for educational and learning purposes.</p>
<p>⭐ If you find this project helpful, feel free to star the repository!</p>
<p>© 2026 <strong><a href = "https://bemaurya.github.io">BeMaurya</a></strong>. All rights reserved.</p>
</div>

