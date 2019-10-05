# RangHiRang

Demo App to show :
* Implementation of Pagination
* Login/Logout function
* ViewModel

### Tech

RangHiRang is made using following libraries and Architectural Pattern:

* [MVVM] - Android Architechtural Pattern.
* [DataBinding] - Used Databinding
* [Retrofit] - Retrofit is used for Network Requests and Caching them.

### Installation

Download the apk and install the app.

### Development (Tech Logic)

-Fetch the data from API
-Contains Page Count
-One page Contains list of 6 colors
Sample API:
```sh
{
  "page": 1,
  "per_page": 6,
  "total": 12,
  "total_pages": 2,
  "data": [
    {
      "id": 1,
      "name": "cerulean",
      "year": 2000,
      "color": "#98B2D1",
      "pantone_value": "15-4020"
    },
    {----},
    {----},
    {----},
    {----},
    {----}
  ]
}
```
-Load Data in Recycler View
-Two Pojo Classes one for Colors, and one for ColorsList
-While scrolling once we reach the visible item in recylcer is last, and pages still exists make api call

### Todos
 - Implement Unit Test
