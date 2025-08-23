# 📱 Quiz App  

An interactive **Quiz Application** built with **Kotlin** using **MVVM architecture pattern** and **Clean Architecture principles**.  
The app provides multiple-choice questions with real-time feedback, score tracking, and smooth navigation across multiple activities.  

---

## ✨ Features  
- Multiple-choice quiz questions  
- Instant feedback on correct/wrong answers  
- Score tracking during the quiz  
- Multi-Activity navigation  
- Cloud-based data storage with **Firebase Firestore**  
- Smooth performance with **Coroutines**  
- Scalable and maintainable architecture with **Hilt Dependency Injection**  
- Separation of concerns with **Domain layer** (Repository & Entities)  

---

## 🛠 Tech Stack  
- **Kotlin** – Main programming language  
- **XML** – UI design  
- **Coroutines** – Asynchronous operations  
- **Hilt** – Dependency Injection  
- **Firebase Firestore** – Cloud database  
- **MVVM + Clean Architecture** – Clear separation between UI, Domain, and Data layers  

---

## 📂 Project Structure  

```
data/      # Repository implementations, Firestore integration
domain/    # Repository interfaces , Entities
di/        # Hilt modules for Dependency Injection  
ui/        # Activities, ViewModels, Adapters  
utils/     # Helper classes & constants  
```

---

## 🚀 Future Improvements  
- Add Firebase Authentication (Google/Email login)  
- Add categories and difficulty levels  
- Add countdown timer for each question  
- Support offline mode with Room database  
- Dark/Light theme support  

---

