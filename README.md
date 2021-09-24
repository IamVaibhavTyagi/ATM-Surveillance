# ATM-Surveillance

Implemented a system to monitor movements in restricted areas with the help of CCTV camera around the ATM. Formed an anti-theft module that triggered the alarm and notified security personnel through message in an attempt of theft and false access.

## Technology used:

- Java
- OpenCV library
- SQL

## About The Project:

- The system was built using Java and OpenCV Library for front-end and movement detection, and used SQL to maintain the audit log.
- Any movement under the defined restricted area would trigger the alarm and messaging module.
- Additonally, an audit log was maintained for every restricted movement with time and date which could be accessed by security personnel using login credentials.
- Created a function to ensure the transaction happened only when the user's face was completely visible.

### Demonstration:

Login screen for the security personnel to access the system using credentials.

<img width="500" height="400" alt="2019-04-10" src="https://user-images.githubusercontent.com/39727591/134587127-1a791d9b-162b-4d87-8718-662354a467a1.png">

Main Menu Screen for the security personnel to access the audit report and visit tracking application.

<img width="600" height="400" alt="2019-04-10 (2)" src="https://user-images.githubusercontent.com/39727591/134587121-2abc7e52-fc6d-4677-97b0-b841607f3011.png">

Audit log records the detail of each enterance in the restricted area.

<img width="550" height="400" alt="2019-04-10 (1)" src="https://user-images.githubusercontent.com/39727591/134587119-241983fd-1190-4f83-9188-45d60af53837.png">

<!-- ![Screenshot 2021-09-23 162855](https://user-images.githubusercontent.com/39727591/134587117-79c1752a-7ae6-453c-b22d-afd8ab3d9be5.png) -->

The login screen for the customers to enter their ATM details.

![Screenshot 2021-09-23 162822](https://user-images.githubusercontent.com/39727591/134587128-776986b4-2e6f-425f-bb07-ba3d76033550.png)

User will be able to enter details only if the face is visible to the camera.

<img width="510" alt="2019-04-10 (7)" src="https://user-images.githubusercontent.com/39727591/134587125-655d9ac4-9af9-4bf5-8d2b-9b6edaebb252.png">

Surveillance screen showcases the detection of any movement in the restricted area.

<img width="587" alt="2019-04-10 (3)" src="https://user-images.githubusercontent.com/39727591/134587122-ed9488d7-0f57-461b-9b42-7290c3c0e939.png">

If there is any movement in the restricted area defined (Rectangular box) then the messaging and alarm module would be triggered.

![2019-04-10 _8](https://user-images.githubusercontent.com/39727591/134587126-dbb60048-a920-4155-8b00-e0a84df24598.png)
