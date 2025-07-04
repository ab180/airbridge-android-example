# Airbridge Android v2 MMP Plan Example

## :link: Test Deep Link

**Links to click**

- **Store fallback tracking link**: [`https://abr.ge/iadolf`](https://abr.ge/iadolf)

    - Google Play Store fallback lands on the Something went wrong (App not found) page because example app is not published on Google Play Store.
  
- **Web fallback tracking link**: [`https://abr.ge/mrnoxk`](https://abr.ge/mrnoxk)

    - The web fallback path is set to https://ab180.github.io/airbridge-web-example/?app=exabr

- **[airbridge-web-example](https://github.com/ab180/airbridge-web-example) link**: [`https://ab180.github.io/airbridge-web-example/?app=exabr`](https://ab180.github.io/airbridge-web-example/?app=exabr)
 
    <img src="./../screenshot/main_page.png"  width="600">

    > User moves to [AB180 Blog](https://play.google.com/store/apps/details?id=product.dp.io.ab180blog)'s Google Play Store alternatively because example app is not published on Google Play Store.

  
### How deep link works

- If example app is installed,
  - Then user moves to example app with `exabr://deeplink` URL.
- If example app is not installed,
  - Then
    - If link is Store fallback tracking link or [airbridge-web-example](https://github.com/ab180/airbridge-web-example) link, user moves to Google play store.
    - If link is Web fallback tracking link, user moves to Web page.
  - And then if user install example app and open it.
  - And then user moves to example app with `exabr://deeplink` URL. (Airbridge's deferred deeplink feature)
    > Airbridge's deferred deeplink feature

```mermaid
flowchart LR
    User --> Android
    Android --> Android-App-Is-Installed[App is installed]
    Android --> Android-App-Is-Not-Installed[App is not installed]
    Android-App-Is-Installed --URL---> Android-App[Android App]
    Android-App-Is-Not-Installed --> Fallback{Fallback}
    Fallback -- Store Fallback --> Google-Play-Store[Google play store]
    Google-Play-Store --URL--> Android-App[Android App]
    Fallback -- Web Fallback --> Web-Page[Web page]
    Web-Page --URL--> Android-App[Android App]
```

#### <!-- Display the deep link callback dialog -->
<details>
<summary><b>Display the deep link callback dialog</b></summary>

| <img src="./../screenshot/Screenshot_deep link_callback_dialog.jpg"  height="600"> |
| :--- |
| Deep link callback Dialog |

</details>

#### <!-- Check on Real-time Logs -->
<details>
<summary><b>Check on Real-time Logs</b></summary>

| <img src="./../screenshot/Screenshot_deep link_log.png"  width="1000"> |
| :--- |
| The results will show on the "Airbridge dashboard → `Raw Data` → `App Real-time Log`" tab if everything is working. |

</details>

## :package: Sending Events

#### <!-- How to send events -->
<details>
<summary><b>How to send events</b></summary>

| <img src="./../screenshot/Screenshot_track_event_1.jpg"  height="600"> <img src="./../screenshot/Screenshot_track_event_2.jpg"  height="600"> |
| :--- |
| Click `Track Event` button to send event |

</details>

#### <!-- Check on Real-time Logs -->
<details>
<summary><b>Check on Real-time Logs</b></summary>

| <img src="./../screenshot/Screenshot_track_event_log.png"  width="1000"> |
| :--- |
| Event information sent from the Airbridge SDK should be seen in the "Airbridge dashboard → `Raw Data` → `App Real-time Log`" tab. |

</details>

## :globe_with_meridians: Hybrid App Setup

#### <!-- How to send events from webview -->
<details>
<summary><b>How to send events from webview</b></summary>

| <img src="./../screenshot/Screenshot_track_event_in_webview_1.jpg"  height="600"> <img src="./../screenshot/Screenshot_track_event_in_webview_2.jpg"  height="600"> |
| :--- |
| 1. Click `Track Event From Web` button to go to the web view screen<br/>2. Click `Track Event` button to send event |

</details>

#### <!-- Check on Real-time Logs -->
<details>
<summary><b>Check on Real-time Logs</b></summary>

| <img src="./../screenshot/Screenshot_track_event_in_webview_log.png"  width="1000"> |
| :--- |
| Event information sent from the Airbridge SDK should be seen in the "Airbridge dashboard → `Raw Data` → `App Real-time Log`" tab. |

</details>