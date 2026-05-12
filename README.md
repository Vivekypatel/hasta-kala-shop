# HastaKalaShop

HastaKalaShop is an Android sales analytics app for artisans, handmade product sellers, and small craft businesses. It helps a shop owner log every sale with product category, color or design, amount, and timestamp, then turns those saved entries into sales summaries, best-seller breakdowns, charts, transaction history, and simple business recommendations.

The app is built as an offline-first Kotlin project using Jetpack Compose, Room, MVVM, Material 3, and MPAndroidChart, with optional Firebase sync placeholders and a mock Gemini-style insight generator.

#Apk link https://drive.google.com/file/d/1Zva__R4bU0e4590ngP7qlinyTHp6KHAb/view?usp=sharing

## App Preview

<table>
  <tr>
    <td align="center"><strong>Splash</strong></td>
    <td align="center"><strong>Home Dashboard</strong></td>
    <td align="center"><strong>Quick Bill</strong></td>
  </tr>
  <tr>
    <td align="center"><img src="docs/screenshots/splash.jpeg" alt="Splash Screen" width="220"/></td>
    <td align="center"><img src="docs/screenshots/home-top.jpeg" alt="Home Dashboard Top" width="220"/></td>
    <td align="center"><img src="docs/screenshots/quick-bill-form.jpeg" alt="Quick Bill Form" width="220"/></td>
  </tr>
  <tr>
    <td align="center">Splash screen</td>
    <td align="center">Sales dashboard top section</td>
    <td align="center">Quick bill entry screen</td>
  </tr>
  <tr>
    <td align="center"><strong>Quick Bill Dropdown</strong></td>
    <td align="center"><strong>Best Sellers Filter</strong></td>
    <td align="center"><strong>Best Sellers Charts</strong></td>
  </tr>
  <tr>
    <td align="center"><img src="docs/screenshots/quick-bill-dropdown.jpeg" alt="Quick Bill Dropdown" width="220"/></td>
    <td align="center"><img src="docs/screenshots/best-sellers-filter.jpeg" alt="Best Sellers Filter" width="220"/></td>
    <td align="center"><img src="docs/screenshots/best-sellers-chart.jpeg" alt="Best Sellers Charts" width="220"/></td>
  </tr>
  <tr>
    <td align="center">Category dropdown interaction</td>
    <td align="center">Week and month filter view</td>
    <td align="center">Pie chart and category revenue chart</td>
  </tr>
  <tr>
    <td align="center"><strong>Home Dashboard Bottom</strong></td>
    <td align="center"><strong>Income Log</strong></td>
    <td align="center"></td>
  </tr>
  <tr>
    <td align="center"><img src="docs/screenshots/home-bottom.jpeg" alt="Home Dashboard Bottom" width="220"/></td>
    <td align="center"><img src="docs/screenshots/income-log.jpeg" alt="Income Log" width="220"/></td>
    <td align="center"></td>
  </tr>
  <tr>
    <td align="center">Best seller analysis and recommendation section</td>
    <td align="center">Income history and summary screen</td>
    <td align="center"></td>
  </tr>
</table>

## Core Flow

### Splash

Shows the HastaKalaShop logo and opens into the main dashboard.

### Home Dashboard

Displays:

- Today sales total
- Weekly sales total
- Monthly sales total
- Best selling product category
- Top selling color
- Best seller pie chart
- Business recommendation generated from saved local sales

### Quick Bill

Lets the user:

- Choose a product category
- Choose a color or design
- Enter the sale amount
- Save the sale with an automatic timestamp

### Best Sellers

Shows:

- Weekly and monthly filters
- Product and color share pie chart
- Category revenue bar chart

### Income Log

Displays:

- Weekly and monthly transaction filters
- Total income
- Number of sales
- Full transaction list with product, color, amount, and date

## Highlights

- Native Android Studio app written fully in Kotlin.
- MVVM architecture with clean separation between UI, ViewModel, repository, and database.
- Jetpack Compose Material 3 interface with warm artisan-inspired colors.
- Room database for offline local sales storage.
- Quick sale logger for bag, keychain, and jewelry entries.
- Weekly and monthly filtering across analytics and history screens.
- Pie chart best-seller analysis and bar chart category revenue breakdown.
- Mock recommendation engine using `generateInsight(salesData: List<Sale>)`.
- Optional Firebase Firestore sync service placeholder included for future cloud integration.
- Custom app branding with splash screen and logo assets.

## Tech Stack

- Kotlin
- Android Gradle Plugin 8.5.2
- Jetpack Compose with Material 3
- AndroidX Navigation Compose
- Room persistence library
- Kotlin coroutines and Flow
- MPAndroidChart
- Firebase Firestore KTX placeholder support
- JUnit dependency setup

## Project Structure

```text
app/src/main/java/com/hastakala/shop
  MainActivity.kt                         Compose entry point and ViewModel setup
  ai/
    GeminiInsightMock.kt                 Mock recommendation insight generator
  data/
    local/
      HastaKalaDatabase.kt               Room database configuration
      Sale.kt                            Sale entity
      SaleDao.kt                         Sale DAO queries
    remote/
      FirebaseSyncService.kt             Optional Firestore sync placeholder
    repository/
      SalesRepository.kt                 Repository layer
  model/
    SalesFilter.kt                       Weekly and monthly filter model
    SalesSummary.kt                      Aggregated dashboard summary model
  ui/
    HastaKalaApp.kt                      Navigation host and screen routes
    components/
      ChartViews.kt                      Pie chart and bar chart Compose wrappers
      CommonUi.kt                        Shared cards, headers, format helpers
    screens/
      SplashScreen.kt                    Splash UI
      HomeDashboardScreen.kt             Dashboard UI
      AddSaleScreen.kt                   Quick bill UI
      BestSellerScreen.kt                Best seller analytics UI
      IncomeLogScreen.kt                 Income history UI
    theme/
      Color.kt                           App color palette
      Theme.kt                           Material theme setup
      Type.kt                            Typography setup
  viewmodel/
    SalesUiState.kt                      Screen state holder
    SalesViewModel.kt                    State, summaries, filters, save actions
```

## Core Screens

- Splash
- Home dashboard
- Quick bill
- Best sellers
- Income log

## Analytics Model

All analytics are derived from saved `Sale` rows in Room.

- Today Total = sum of sales from start of current day to now
- Week Total = sum of sales from the last 7 days
- Month Total = sum of sales from the current month
- Best Selling Product = category with the highest sale count
- Top Color = color with the highest sale count
- Total Income = sum of all filtered transaction amounts
- Number Of Sales = count of filtered sale rows

Best-seller views group data by combinations like `Red Bag` or `Blue Jewelry`, while revenue charts group totals by category.

## Recommendation Insight

The dashboard includes a local recommendation section powered by a mock helper:

```kotlin
fun generateInsight(salesData: List<Sale>): String
```

The current implementation works offline and generates trend-style suggestions from saved sales data, such as identifying the strongest product-color combination and the highest revenue category.

## Optional Cloud Sync

The project includes a `FirebaseSyncService` file as a placeholder for optional Firestore integration. The default app experience works fully offline without Firebase setup.

## Getting Started

### Prerequisites

- Android Studio
- JDK 17 or Android Studio embedded JDK
- Android SDK with compile SDK 35
- Emulator or Android device running Android 8.0 or newer

### Run The App

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Start an emulator or connect an Android device.
4. Select the `app` run configuration.
5. Run the project.

## Command Line Build

```powershell
.\gradlew.bat assembleDebug
```

## Typical Workflow

1. Open the dashboard and review current sales totals.
2. Tap `Add Sale`.
3. Select product category and color or design.
4. Enter the sale amount.
5. Save the sale to Room with an automatic timestamp.
6. Return to the dashboard to see totals and charts update.
7. Open Best Sellers to compare weekly and monthly trends.
8. Open Income Log to review transactions and total earnings.

## Data And Privacy

- Sales are stored locally in Room by default.
- The recommendation text is generated locally from stored data.
- Firebase support is optional and not required for normal usage.
- No external cloud service is required to use the app in its current form.

## Current Status

HastaKalaShop is an MVP focused on offline artisan sales logging and simple analytics. Strong next steps would be adding real Firebase sync, export and sharing features for reports, editable transactions, richer charts, and a real AI provider integration beyond the current mock insight generator.
