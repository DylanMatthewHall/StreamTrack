# StreamTrack  

## About and Goal  

**Problem:** With multiple streaming and subscription services, it is difficult for users to track actual usage and determine the true value of their memberships.  

**Audience:** Any individual who subscribes to paid memberships for streaming platforms (e.g., Netflix, Twitch, YouTube Premium) or gaming services (e.g., War Thunder Premium, Wizard101, World of Warcraft).  

**Statement:** StreamTrack helps users by collecting subscription costs and logged usage time to generate a clear *cost-per-hour analysis*. This enables users to make informed decisions about whether each service is worth keeping.  

**Goal:** Provide users with a simple, transparent way to evaluate the value of their subscriptions through clear reporting and recommendations.  


## Requirements MVP

**Functional Requirements:** 
1. The system must allow user to **add a service** with a monthly cost.
2. The system must allow user to **log a usage session** for a service. (service, duration, date)
3. The system must **calculate total hours watched** per service.
4. The system must **calculate cost per hour** = (month cost) / (total hours watched).
5. The system must **output a report** of all services.
    - 5.1 The report must show each services name, monthly cost, total hours watched, and cost per hour.
    - 5.2 The report must display the services sorted from best cost per hour to worst.
    - 5.3 The report must handle cases where no hours are logged as N/A.

**Non Functional Requirements**
1. **Usuability:** Provide a simple text menu for adding services, logging sessions, and generating reports
2. **Accuracy:** Handle edge cases such as zero hours watched as (N/A) and place these at top of list in an identifying manner.


