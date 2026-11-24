# Changelog

## 0.7.0

- **Your own colors and font** under `page.theme`, for light and dark, or always one of them (`mode: dark`). Every color pair the page uses is checked against WCAG AA first; if one fails, the whole theme is left out and the console names the pair. The theme is served as `/theme.css` after the page's own stylesheet, so the strict CSP stays as it is.
- New welcome view layout: a greeting, then boxed sections for joining, the first steps and the map. Phones get one column, and from 52rem wide the first steps and the map sit side by side. Sign-out is a quiet text button. Smaller text.
- New `page.banner` setting: a PNG or WebP in `plugins/WelcomeWagon/`, at most 512 KB, served at `/banner`. Empty means no banner. A bad one is left out with a console warning. With a banner, the page name stays as a hidden heading and the picture's alt text.
- The default `page.steps` lines are shorter.
- Tested: unit and HTTP tests (new ones for the banner settings, the `/banner` answer, heading order, the no-banner page and a lone box in the grid), the bot suite on Paper 26.1.2, and screenshots at 320, 390 and 1280 px in headless Chromium. Not tested: 26.2 and 26.3 bot runs, a real phone, a screen reader.

Older release notes are in `docs/releases/`.
