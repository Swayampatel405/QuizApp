<h1 align="center">📚 Quiz App 🎯</h1>
<p align="center">
  An interactive Quiz App built using <b>Kotlin, Jetpack Compose, Dagger Hilt, and Retrofit</b>, fetching questions dynamically from the <b>Trivia API</b>.
</p>

---

## 🚀 Features

### 🏠 Home Screen
- Dropdowns to select:  
  ✅ **Category**  
  ✅ **Number of Questions**  
  ✅ **Question Type** (Multiple Choice/True or False)  
  ✅ **Difficulty Level** (Easy/Medium/Hard)  
- **Generate Quiz Button** to fetch questions based on user selection.

### 🔐 Authentication (Implemented ✅)
- **Firebase Authentication**: Users can log in or sign up using email and password.
- **Auto Login Feature**: If a user has logged in before, they will be redirected to the app automatically unless they log out.
- **Logout Option**: Users can log out anytime, requiring them to log in again on the next app launch.

### ❓ Quiz Screen
- Displays one question at a time.
- Multiple-choice options with selection feedback.
- **Next Question Button** to navigate through questions.
- **Timer (Coming Soon ⏳).**

### 🏆 Result Screen
- **Score Display** (percentage-based).
- **Total Questions Attempted & Correct Answers Count**.
- **Detailed Review Section**:
  - ✅ Full Question
  - ❌ User's Selected Answer (Incorrect answers in red)
  - ✅ Correct Answer (Highlighted)
- **Hide/Show Answers Button** for better UI experience.
- **Restart Quiz Button** to retake the quiz.

### 🧑‍💼 Profile Section
- Displays **User's Name & Email**.
- Logout button.

---

## 🛠 Tech Stack

| Technology  | Purpose  |
|-------------|---------|
| **Kotlin** | Programming Language |
| **Jetpack Compose** | UI Framework |
| **Dagger Hilt** | Dependency Injection |
| **Retrofit** | API Integration |
| **Firebase Authentication** | User Login & Auto Login |
| **Trivia API** | Fetches quiz questions dynamically |

---

## 🔧 Installation & Setup

1. Clone the repository:  
   ```sh
   git clone https://github.com/Swayampatel405/QuizApp.git
