<h1 align="center">  
  Pixabay Coding Challenge
</h1>  
<p align="center">This repository contains the Pixabay coding challenge application.  </p>  

The main purpose of this project is to showcase my coding abilities and software engineering know-how. The project can be extended to contain further features and improvements in the future.

- The app is built 100% in Kotlin.  
- The UI is designed with Jetpack Compose.  
- MVVM architecture has been followed.  
- Coroutines for asynchronous logic.  
- UI updates using Kotlin StateFlows.  
- The app follows the Material design guidelines.  
- The app follows the DRY & SOLID best practices.  
- The configuration change handling is implemented. (e.g. screen rotation)  
- Based on the device size and orientation, either ListScreen is only shown, or ListScreen & DetailScreen are both shown side by side.  
- Clean architecture design is created with a separation of concerns. (modules: presentation, domain, data, di)  
- Tech Stack: MVVM Architecture, Android Architecture Components, Jetpack Compose, Coroutines, Retrofit, Coil.  
  

## ✅ Features: 
- Users can search images by entering one or more words in the text field.  
- Images are requested from the Pixabay API.  
- A grid of images is displayed to the user.  
- Each grid item has a thumbnail, Pixabay username, and image tags.  
- The server responses are cached in memory and storage for offline handling.  
- A click on the image thumbnail shows a dialog box asking for the user's confirmation to view details.  
- In case of a positive answer, the detail screen is shown.  
- The detail screen shows the image in the highest resolution, the name of the user, the list of image .tags, the number of likes, the number of downloads, and the number of comments.  
- When the app starts, the search keyword "fruits" is searched by default.  
 



## ⚡️ Prerequisites
```bash  
# You will need an API key for the Pixabay public web services. It can be retrieved from this page (you must be logged in to see it):
'https://pixabay.com/api/docs/#api_search_images'  
Please put your `API_KEY` in "local.properties" file which should be located in the root directory. 
Example of how the line of code looks like: 

API_KEY=87654321-0d123456789dc12b345678cf9
```  
That's all you need to know to start! 🎉


## ⭐️ Demo

#### 📺 Full demo video: https://www.youtube.com/watch?v=yiFBtxg7Slk

## ⭐️ Screenshots:

<p align="center">

### Phone:

<img src="https://user-images.githubusercontent.com/19203471/216852107-318f0f79-0fa4-4d85-ae88-a2485fe9b716.png" width=50%>




<img src="https://user-images.githubusercontent.com/19203471/216852108-3801d353-8608-4343-9573-6468c7b2ab47.png" width=50%>





<img src="https://user-images.githubusercontent.com/19203471/216852109-70bca63f-7494-43b4-85c0-ff1494d35a82.png" width=50%>





<img src="https://user-images.githubusercontent.com/19203471/216852111-73628484-b007-4bf3-8959-a4ddeaf241ae.png" width=50%>





<img src="https://user-images.githubusercontent.com/19203471/216852112-0b80e2c6-cc5a-45b6-875d-75a2e335ad44.png" width=50%>





<img src="https://user-images.githubusercontent.com/19203471/216852113-b0a2bcb2-463d-42d6-a3ab-55f19d323e61.png" width=50%>




<img src="https://user-images.githubusercontent.com/19203471/216852114-4abc9fbf-eb62-49b3-8fad-8e1e38911fb9.png" width=50%>






<img src="https://user-images.githubusercontent.com/19203471/216852115-6c027bac-8922-4703-9207-648f6c2b15f5.png" width=50%>




### Tablet:
<img src="https://user-images.githubusercontent.com/19203471/216852116-325c5f4e-7197-44ba-a617-470e140eb502.png">




<img src="https://user-images.githubusercontent.com/19203471/216852118-edb99a42-3723-445f-82de-bba6fbd54829.png">






<img src="https://user-images.githubusercontent.com/19203471/216852119-00297eb2-4df4-413b-8ee0-a748df7b7fd2.png">





<img src="https://user-images.githubusercontent.com/19203471/216852123-47284a24-5916-4f4f-ba6c-c5f2df2746bb.png">





<img src="https://user-images.githubusercontent.com/19203471/216852124-06876a8a-f607-49fb-aa96-4e3bb55d8107.png">






<img src="https://user-images.githubusercontent.com/19203471/216852125-3071de59-942c-45e4-8dcf-59ca8fe7c12b.png">





</p>
