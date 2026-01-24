# 🚀 Service Status Page

> A modern, production-ready status monitoring application built from scratch to track service health and manage incidents in real-time.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

## 💡 What is This?

Ever wondered how companies like GitHub, AWS, or Stripe show their "All Systems Operational" pages? This is my take on building one from scratch using Spring Boot.

It's a full-stack web application where you can:
- Monitor multiple services (API, database, website, etc.)
- Report and track incidents when things go wrong
- Display real-time status to your users
- Manage everything through a secure admin panel

**Live Demo:** _(Add your deployment link here)_

## ✨ Key Features

### For Users (Public Pages)
- 📊 **Live Status Dashboard** - See system health at a glance
- 🔍 **Incident History** - Browse past issues and resolutions
- 📱 **Responsive Design** - Works perfectly on mobile and desktop

### For Admins (Protected Panel)
- 🔐 **Secure Login** - Spring Security authentication
- ➕ **Service Management** - Add, edit, or remove monitored services
- 🚨 **Incident Tracking** - Create incidents with severity levels (Minor → Major → Critical)
- 📝 **Status Updates** - Change service status in real-time
- 🗑️ **Full CRUD Operations** - Complete control over all data

## 🛠️ Built With

I chose a modern, industry-standard tech stack:

| Technology | Purpose | Why I Chose It |
|------------|---------|----------------|
| **Spring Boot 3.5** | Backend Framework | Industry standard for enterprise Java |
| **Spring Security** | Authentication | Secure admin panel with encryption |
| **Spring Data JPA** | Database Layer | Simplified database operations |
| **Hibernate** | ORM | Automatic SQL generation |
| **Thymeleaf** | Template Engine | Server-side HTML rendering |
| **Bootstrap 5** | UI Framework | Professional, responsive design |
| **H2 Database** | Storage | Fast in-memory database (perfect for demos) |
| **Maven** | Build Tool | Dependency management |
| **JUnit 5** | Testing | 6 unit tests with 100% pass rate |

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher ([Download](https://adoptium.net/))
- Maven 3.6+ (comes with most Java installations)
- Any IDE (I use IntelliJ IDEA)

### Installation

1️⃣ **Clone the repository**
```bash
git clone https://github.com/huharun/statuspage.git
cd statuspage
```

2️⃣ **Build the project**
```bash
mvn clean install
```

3️⃣ **Run the application**
```bash
mvn spring-boot:run
```

4️⃣ **Open your browser**
```
http://localhost:8080
```

That's it! The app will start with sample data already loaded.

## 🎮 How to Use

### Viewing Status (No Login Required)
1. Visit `http://localhost:8080/`
2. See all services and their current status
3. Check active incidents and their progress
4. View complete incident history at `/history`

### Admin Panel (Login Required)
1. Go to `http://localhost:8080/login`
2. Login with:
    - **Username:** `admin`
    - **Password:** `admin123`
3. You can now:
    - Add new services to monitor
    - Update service status (Operational → Degraded → Outage)
    - Report new incidents
    - Update incident status as you resolve issues
    - Delete services or incidents

### Database Console (For Developers)
Want to see the raw data?
1. Visit `http://localhost:8080/h2-console`
2. Use these settings:
    - **JDBC URL:** `jdbc:h2:mem:statuspagedb`
    - **Username:** `sa`
    - **Password:** _(leave empty)_

## 📁 Project Structure

I organized this project following Spring Boot best practices:

```
statuspage/
├── src/main/java/com/arun/statuspage/
│   ├── config/                      # Configuration files
│   │   ├── SecurityConfig.java      # Login & security setup
│   │   └── DataInitializer.java     # Loads sample data on startup
│   │
│   ├── controller/                  # Handles web requests
│   │   ├── StatusController.java    # Public pages (/, /history)
│   │   ├── AdminController.java     # Admin dashboard
│   │   └── LoginController.java     # Login page
│   │
│   ├── model/                       # Database entities
│   │   ├── Service.java             # Service monitoring entity
│   │   ├── Incident.java            # Incident tracking entity
│   │   └── [Status Enums]           # Status levels & severities
│   │
│   ├── repository/                  # Database access layer
│   │   ├── ServiceRepository.java   # Service CRUD operations
│   │   └── IncidentRepository.java  # Incident CRUD operations
│   │
│   └── exception/                   # Error handling
│       └── GlobalExceptionHandler.java  # Custom error pages
│
├── src/main/resources/
│   ├── templates/                   # HTML pages (Thymeleaf)
│   │   ├── index.html              # Homepage
│   │   ├── history.html            # Incident history
│   │   ├── login.html              # Login page
│   │   ├── error.html              # Error page
│   │   └── admin/                  # Admin panel pages
│   │
│   └── application.properties       # App configuration
│
└── src/test/java/                   # Unit tests
    ├── StatuspageApplicationTests.java
    ├── ServiceRepositoryTest.java   # Tests database operations
    └── StatusControllerTest.java    # Tests web endpoints
```

## 🧪 Testing

I wrote comprehensive tests to ensure everything works:

```bash
# Run all tests
mvn test

# You should see:
# Tests run: 6, Failures: 0, Errors: 0 ✅
```

**Test Coverage:**
- ✅ Application context loads correctly
- ✅ Services can be saved and retrieved
- ✅ Services are sorted by display order
- ✅ Service status can be updated
- ✅ Public pages load successfully
- ✅ Controllers handle requests properly

## ⚙️ Configuration

### Changing Admin Password

Edit `src/main/java/com/arun/statuspage/config/SecurityConfig.java`:

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails admin = User.builder()
        .username("your_username")        // ← Change this
        .password(passwordEncoder().encode("your_password"))  // ← Change this
        .roles("ADMIN")
        .build();
    return new InMemoryUserDetailsManager(admin);
}
```

### Using a Persistent Database

**Current setup:** Data is lost when you restart (H2 in-memory)

**Want permanent storage?** Update `application.properties`:

**Option 1: File-based H2** (data survives restarts)
```properties
spring.datasource.url=jdbc:h2:file:./data/statuspagedb
```

**Option 2: PostgreSQL** (production-ready)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/statuspage
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Option 3: MySQL** (also production-ready)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/statuspage
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## 🌐 Deployment

### Deploy to Railway (Free, Recommended)

1. Push your code to GitHub
2. Go to [Railway.app](https://railway.app)
3. Sign up with GitHub
4. Click "New Project" → "Deploy from GitHub"
5. Select this repository
6. Railway auto-detects Spring Boot and deploys! ✨

### Deploy to Render (Also Free)

1. Go to [Render.com](https://render.com)
2. Connect your GitHub repository
3. Set these build settings:
    - **Build Command:** `mvn clean install`
    - **Start Command:** `java -jar target/statuspage-0.0.1-SNAPSHOT.jar`
4. Deploy!

### Build JAR for Self-Hosting

```bash
mvn clean package
java -jar target/statuspage-0.0.1-SNAPSHOT.jar
```

Now you can run this anywhere with Java installed!

## 🎯 What I Learned

Building this project taught me:

- 📚 **Spring Boot Architecture** - How MVC pattern works in real applications
- 🔒 **Security** - Implementing authentication and authorization
- 💾 **JPA & Hibernate** - Working with databases using ORM
- 🎨 **Full-Stack Development** - Connecting backend to frontend
- 🧪 **Testing** - Writing meaningful unit tests
- 🏗️ **Project Structure** - Organizing code professionally
- 🚀 **Deployment** - Taking an app from localhost to production

## 🔮 Future Improvements

Ideas I'm considering:

- [ ] REST API endpoints for programmatic access
- [ ] Email notifications when incidents are reported
- [ ] Scheduled health checks (ping services automatically)
- [ ] User roles (Admin, Moderator, Viewer)
- [ ] Incident update timeline with multiple status updates
- [ ] Service uptime percentage graphs
- [ ] Custom branding (logo, colors, company name)
- [ ] Docker containerization
- [ ] Metrics dashboard with Grafana

## 📝 License

This project is open source and available under the [MIT License](LICENSE).


## 🙏 Acknowledgments

- Spring Boot team for amazing documentation
- Bootstrap for the UI framework
- Stack Overflow community for helping debug issues

---

**Built with ❤️ and lots of ☕**