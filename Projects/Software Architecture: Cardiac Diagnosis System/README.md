# Cardiac Diagnosis System Architecture 🫀💻

## Overview
This project presents the foundational software engineering design and architectural planning for a Cardiac Diagnosis System. It defines the system's structural and behavioral models, detailing how users interact with the system to manage patient data, process medical telemetry, and automate diagnostic reporting. The design ensures coherence between use-case requirements, sequence flows, and entity relationships.

## Core Use Cases
* **User & Patient Registration:** Allows healthcare professionals and system administrators to securely register accounts and create new patient profiles linked to specific providers.
* **Collect ECG Data:** Details the process of recording patient data via an ECG monitor and transmitting it into a digital format for system storage.
* **Analyze ECG Data:** Outlines how the system's engine processes stored ECG records to detect anomalies and return diagnostic results to medical staff.
* **Generate Report & View History:** Covers the generation of detailed diagnostic reports and the retrieval of chronological patient histories to aid in ongoing medical evaluation.

## System Entities & Class Structure
The system's architecture is built upon several core entities, explicitly mapped in UML class diagrams:
* **HealthcareProfessional:** Manages patient records, initiates ECG data collection, and requests report generation. Includes attributes like `id`, `name`, and `email`.
* **SystemAdmin:** Oversees user permissions, report management, and overall system security maintenance.
* **AnalysisEngine:** The central processing unit responsible for executing the `retrieveECGData()` and `analyzeECGData()` operations to detect cardiac abnormalities.
* **Patient:** Represents the medical subject, storing details like `id`, `name`, and `dob`.
* **ECGData:** Holds the raw telemetry data, tracked via `recordId`, `timestamp`, and a `waveform` byte array.
* **Report:** Encapsulates the final analysis findings, structured with a `reportId` and a `diagnosticSummary`.

## Design Methodology
The project demonstrates a high degree of consistency between sequence and class diagrams, effectively encapsulating the complex "Analyze ECG Data" workflow. It incorporates unique identifiers and specific operational methods to mirror the robust functionality required in a real-world clinical application.