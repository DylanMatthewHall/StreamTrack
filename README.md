# StreamTrack  

## About  

With so many streaming and subscription services, it’s difficult to track actual usage and determine the true value of memberships. **StreamTrack** is a **Java console application** 
that helps users manage subscriptions and log viewing sessions. By combining subscription cost with usage data, the app calculates a clear **cost-per-hour analysis**, helping users make informed decisions about which services are worth keeping.  

**Audience:** Individuals who subscribe to streaming platforms (Netflix, Twitch, YouTube Premium) or gaming services (World of Warcraft, Xbox Game Pass, Wizard101, etc.).  

**Goal:** Provide a simple, transparent way to evaluate subscription value through clear reporting and recommendations.  

---

## Features  

- Add, view, update, and remove streaming services  
- Add, view, update, and remove viewing sessions  
- Persistent storage through **SQLite** so data is saved even after the app is closed  
- Generate reports showing each service’s:  
  - Name and monthly cost  
  - Total hours watched  
  - Cost per hour  
  - Sorted from best value to worst  
- Handle edge cases such as zero usage (displayed as **N/A**)  

---

## Tech Stack  

- **Language:** Java  
- **Database:** SQLite  
- **Architecture:** MVC + DAO design pattern  
- **Tools:** Git/GitHub for version control  

---

## Skills Strengthened  

- Object-Oriented Programming (OOP) in Java  
- Database integration and SQL queries  
- Software architecture with MVC + DAO  
- Clean code practices and input validation  
- Git branching, merging, and version control workflow  

---

## Images of StreamTrack
![Alt text](assets/StreamTackStartMenu.png)

## Next Steps  

- Expand input validation and error handling  
- Convert StreamTrack into a **full-stack web application** (frontend + backend API)  
- Add **data visualization** (graphs/charts) to show subscription value over time  
- Extend reporting with more advanced insights (monthly usage trends, per-session breakdowns)  

---

👉 Check out the repository and code here:  
[StreamTrack on GitHub](https://github.com/DylanMatthewHall/StreamTrack)  
