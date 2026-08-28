# AI Handwritten Math Solver

A full-stack web application that allows users to draw mathematical equations on an HTML5 canvas, recognizes the handwritten characters using a custom AI model, and solves them using a Computer Algebra System (CAS).

## Features

* **Handwriting Recognition**: Accurately recognizes digits and mathematical symbols (+, -, =, x, etc.) drawn by the user.
* **Symbolic Math & Algebra**: Goes beyond simple arithmetic. It can simplify expressions (e.g., x+x -> 2*x) and solve equations (e.g., 3x+2=4 -> x=2/3).
* **Smart Image Preprocessing**: Implements custom "letterboxing" algorithms to preserve the aspect ratio of drawn characters (preventing stretching of symbols like -), scaling them perfectly into a 28x28 pixel canvas for the ONNX model.
* **Vertical Slice Architecture**: Clean separation of concerns on the backend (Facade pattern via CalculatorService, isolated AI and Math services).
* **Fully Containerized**: The entire application (Java backend and Nginx frontend) is orchestrated via Docker Compose for a true zero-configuration startup.

## Tech Stack

### Backend
* **Java 21** & **Spring Boot 3** (REST API)
* **ONNX Runtime**: For fast and efficient AI model inference in Java.
* **Symja (matheclipse-core)**: A powerful Java library for symbolic mathematics and equation solving.
* **Lombok**: For boilerplate code reduction.

### Frontend
* **Vanilla JavaScript** & **HTML5 Canvas**: For capturing user drawings, segmenting individual characters via bounding boxes, and communicating with the API.

### Infrastructure
* **Docker & Docker Compose**: Multi-stage builds for the Java backend and a lightweight Nginx alpine image for serving the frontend.

## How It Works

1. **Draw**: The user draws an equation on the web canvas.
2. **Segment**: The frontend JavaScript scans the canvas, isolates individual characters, and packages them as multiple image files.
3. **Preprocess**: The Spring Boot backend receives the multipart/form-data. Each image is proportionally scaled to a 20x20 box and centered on a 28x28 white background (Letterboxing).
4. **Predict**: The ONNX model analyzes the normalized images and returns the predicted characters.
5. **Evaluate**: The MathEvaluationService parses the recognized string. If an equation is detected (e.g., contains =), it automatically formats it for Symja (e.g., wrapping it in Solve(..., x)). 
6. **Result**: The solved mathematical result is returned as a structured JSON and displayed on the UI.

## API Reference

### POST /calculator/evaluate_result
Consumes: multipart/form-data
* files: A list of character images segmented by the frontend.

**Response (JSON):**
```json
{
  "response": {
    "predictions": [
      { "recognizedSymbol": "3" },
      { "recognizedSymbol": "x" },
      { "recognizedSymbol": "=" },
      { "recognizedSymbol": "6" }
    ]
  },
  "expression": "3x=6",
  "result": "x=2"
}
```

## Setup & Installation

**Prerequisites:**
* Docker (Docker Desktop on Windows/Mac or Docker Engine on Linux)

1. Clone the repository and navigate to the project root directory.
2. Build and start the containers in the background:
   ```bash
   docker compose up -d
   ```
3. Open your web browser and go to:
   ```text
   http://localhost
   ```
4. Start drawing equations!

**To stop the application, run:**
```bash
docker compose down
```
