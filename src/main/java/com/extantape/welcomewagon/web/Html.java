package com.extantape.welcomewagon.web;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * The landing page's HTML. Plain forms that work without JavaScript, with labels, visible focus and WCAG 2.2 AA
 * contrast in light and dark. The one script only moves a sign-in token from the address bar into the form; the CSP
 * allows exactly that script and that stylesheet, by hash.
 */
public final class Html {
    static final String CSS = """
            :root{color-scheme:light dark;--fg:#1a1a1a;--bg:#fff;--muted:#4a4a4a;--line:#767676;--btn:#0b5394;--btnfg:#fff;--bad:#a4161a;--ok:#1b5e20}
            @media (prefers-color-scheme:dark){:root{--fg:#f2f2f2;--bg:#141414;--muted:#c4c4c4;--line:#8a8a8a;--btn:#9fc5f8;--btnfg:#0a0a0a;--bad:#ff9a9a;--ok:#9be29f}}
            body{font:1.125rem/1.5 system-ui,sans-serif;color:var(--fg);background:var(--bg);margin:0;padding:2rem 1rem}
            main{max-width:28rem;margin:0 auto}
            h1{font-size:1.75rem;margin:0 0 1rem}
            p{margin:0 0 1rem}
            .hint{color:var(--muted);font-size:1rem}
            label{display:block;font-weight:600;margin:1rem 0 .25rem}
            input{font:inherit;width:100%;box-sizing:border-box;padding:.6rem .7rem;border:2px solid var(--line);border-radius:.4rem;background:var(--bg);color:var(--fg)}
            button{font:inherit;font-weight:600;margin-top:1.25rem;padding:.65rem 1.25rem;border:0;border-radius:.4rem;background:var(--btn);color:var(--btnfg);cursor:pointer}
            input:focus-visible,button:focus-visible{outline:3px solid var(--fg);outline-offset:2px}
            .bad{color:var(--bad);font-weight:600}
            .ok{color:var(--ok);font-weight:600}
            hr{border:0;border-top:1px solid var(--line);margin:2rem 0}
            a{color:var(--btn)}
            .logo{display:block;max-width:12rem;max-height:8rem;margin:0 auto 1.5rem}
            .addr{display:flex;flex-wrap:wrap;align-items:center;gap:.75rem;margin:0 0 1.5rem}
            .addr code{font:600 1.5rem/1.3 ui-monospace,SFMono-Regular,Menlo,Consolas,"Liberation Mono",monospace;padding:.5rem .75rem;border:2px solid var(--line);border-radius:.4rem;word-break:break-all}
            .addr button{margin:0}
            ol{padding-left:1.25rem}
            li{margin:0 0 1rem}
            li strong{display:block}
            body.b{padding:0}
            .top{max-width:44rem;margin:0 auto}
            .hero{display:block;width:100%;height:auto;image-rendering:pixelated}
            .rim{height:6px;background:repeating-linear-gradient(90deg,var(--btn) 0 12px,var(--bg) 12px 24px)}
            body.b main{padding:1.5rem 1rem 3rem}
            .vh{position:absolute;width:1px;height:1px;overflow:hidden;clip:rect(0 0 0 0);white-space:nowrap}
            body.w{font-size:1rem}
            body.w main{max-width:34rem}
            body.w.b main{padding:0 1rem 3rem}
            body.b:not(.w) main{padding-top:1.5rem}
            .lede{font-size:1.375rem;font-weight:700;margin:1.75rem 0 .125rem}
            .sub{color:var(--muted);margin:0 0 1.5rem}
            section{margin:0 0 1rem;border:1px solid var(--line);border-radius:12px;padding:1.125rem 1.25rem 1.25rem}
            .grid{display:block}
            h2{font-size:.875rem;text-transform:uppercase;letter-spacing:.06em;color:var(--fg);margin:0 0 .75rem}
            .num{list-style:none;padding:0;margin:0;counter-reset:n}
            .num li{counter-increment:n;position:relative;padding-left:2rem;margin:0 0 .75rem;overflow-wrap:anywhere}
            .num li:last-child{margin-bottom:0}
            .num li::before{content:counter(n);position:absolute;left:0;top:.1rem;width:1.375rem;height:1.375rem;border-radius:50%;border:2px solid var(--line);font-weight:700;font-size:.8125rem;display:flex;align-items:center;justify-content:center;box-sizing:border-box}
            .num li strong{display:inline}
            .note{color:var(--muted);font-size:.9375rem;margin:0 0 .75rem}
            .w .addr{margin:.5rem 0 0}
            .w .addr code{font-size:1.25rem;flex:1 1 auto;min-width:min(100%,max-content);word-break:normal;overflow-wrap:anywhere}
            .w .addr button{min-height:44px;flex:none}
            section form{margin:0}
            section p{margin:0 0 1rem}
            section form button{margin-top:0}
            footer{margin:1.5rem 0 0}
            button.quiet{background:transparent;color:var(--muted);border:0;margin:0;font-size:1rem;font-weight:600;min-height:44px;padding:.6rem 0;text-decoration:underline;text-underline-offset:3px}
            @media (min-width:52rem){body.w main{max-width:46rem}.grid:not(.one){display:grid;grid-template-columns:1fr 1fr;gap:1rem;align-items:stretch}.grid:not(.one) section{margin:0}.grid:not(.one) section:last-child{display:flex;flex-direction:column}.grid:not(.one) section:last-child form{margin-top:auto}footer{text-align:center}.w .addr code{flex:0 1 20rem}}
            @media (max-width:30rem){body.w main,body.w.b main{padding-left:.75rem;padding-right:.75rem}.lede{font-size:1.25rem;margin:1.25rem 0 .125rem}.sub{margin:0 0 1rem}section{padding:.875rem .875rem 1rem;margin:0 0 .75rem}h2{margin:0 0 .5rem}.num li{padding-left:1.875rem;margin:0 0 .625rem}.num li::before{width:1.3125rem;height:1.3125rem;top:.125rem}.w .addr code{font-size:1.0625rem;padding:.45rem .6rem}.w .addr{gap:.5rem}footer{margin:1rem 0 0}}
            """;
    static final String SCRIPT =
            "(function(){var t=location.hash.slice(1);if(t){document.getElementById('link').value=t;history.replaceState(null,'',location.pathname);}})();";
    /** Copies the play address. The page works without it: the address is plain text anyone can select. */
    static final String COPY_SCRIPT =
            "(function(){var b=document.getElementById('copy'),a=document.getElementById('addr');if(!b||!a||!navigator.clipboard)return;"
            + "b.hidden=false;b.addEventListener('click',function(){navigator.clipboard.writeText(a.textContent).then(function(){b.textContent='Copied';});});})();";
    /**
     * Fills the invite code from an invite link, which carries it after # so it never reaches a server log (D187), then
     * takes it out of the address bar. The player still presses Continue: nothing is used up by opening a link.
     */
    static final String CODE_SCRIPT =
            "(function(){var t=location.hash.slice(1),c=document.getElementById('code');if(t&&c&&/^[A-Za-z0-9 -]{1,16}$/.test(t)){c.value=t;history.replaceState(null,'',location.pathname);}})();";
    static final String CSP = "default-src 'none'; style-src '" + sha256(CSS) + "'; script-src '" + sha256(SCRIPT) + "' '" + sha256(COPY_SCRIPT) + "' '" + sha256(CODE_SCRIPT)
            + "'; img-src 'self'; form-action 'self'; frame-ancestors 'none'; base-uri 'none'";

    /** The CSP for the welcome view: as {@link #CSP}, but its map button may post on to the map's own address. */
    static String cspWithMap(String mapUrl) {
        return mapUrl.isEmpty() ? CSP : CSP.replace("form-action 'self'", "form-action 'self' " + mapUrl);
    }

    private Html() {}

    public static String esc(String s) {
        StringBuilder b = new StringBuilder(s.length());
        for (char c : s.toCharArray()) switch (c) {
            case '<' -> b.append("&lt;");
            case '>' -> b.append("&gt;");
            case '&' -> b.append("&amp;");
            case '"' -> b.append("&quot;");
            case '\'' -> b.append("&#39;");
            default -> b.append(c);
        }
        return b.toString();
    }

    static String page(String title, String body, boolean withScript) {
        return page(title, body, withScript ? SCRIPT : null);
    }

    static String page(String title, String body, String script) {
        return page(title, body, script, null, false);
    }

    /**
     * The whole page. With a banner in the settings, the picture and its rim sit above the content at the top of the page.
     * {@code wide} is the signed-in welcome view, which has boxed sections and a wider column.
     */
    static String page(String title, String body, String script, PageSettings p, boolean wide) {
        boolean banner = p != null && p.banner() != null;
        String cls = (banner ? "b" : "") + (wide ? (banner ? " w" : "w") : "");
        String top = banner ? "<div class=\"top\"><img class=\"hero\" src=\"" + p.base() + "/banner\" alt=\"" + esc(p.heading()) + "\">"
                + "<div class=\"rim\" aria-hidden=\"true\"></div></div>" : "";
        return "<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">"
                + "<meta name=\"referrer\" content=\"no-referrer\"><title>" + esc(title) + "</title>"
                + "<style>" + CSS + "</style></head><body" + (cls.isEmpty() ? "" : " class=\"" + cls + "\"") + ">" + top + "<main>" + body + "</main>"
                + (script != null ? "<script>" + script + "</script>" : "") + "</body></html>";
    }

    /**
     * The banner, if there is one, is drawn by {@link #page} above everything, and its alt text is the server's name, so
     * the heading stays for screen readers but is hidden from view. Otherwise the logo, if there is one, then the name.
     * The logo's alt text is the name too, so it's never read twice.
     */
    static String header(PageSettings p) {
        if (p.banner() != null) return "<h1 class=\"vh\">" + esc(p.heading()) + "</h1>";
        String img = p.logo() == null ? "" : "<img class=\"logo\" src=\"" + p.base() + "/logo\" alt=\"" + esc(p.heading()) + "\">";
        return img + "<h1" + (p.logo() == null ? "" : " class=\"hint\"") + ">" + esc(p.heading()) + "</h1>";
    }

    /** A message line. Kind is "ok", "bad" or "" (plain). Announced to screen readers when the page loads. */
    static String message(String text, String kind) {
        if (text == null || text.isEmpty()) return "";
        return "<p role=\"status\"" + (kind.isEmpty() ? "" : " class=\"" + kind + "\"") + ">" + esc(text) + "</p>";
    }

    /** The page for someone not signed in: one box for the invite code. Nothing about the server's address is here. */
    static String home(PageSettings p, String csrf, boolean invitesOpen, boolean signInOpen, String msg, String msgKind) {
        StringBuilder b = new StringBuilder(header(p));
        b.append(message(msg, msgKind));
        if (invitesOpen) {
            b.append("<p>If someone invited you, type the code they gave you.</p>")
             .append("<form method=\"post\" action=\"").append(p.base()).append("/invite\">")
             .append("<input type=\"hidden\" name=\"csrf\" value=\"").append(esc(csrf)).append("\">")
             .append("<label for=\"code\">Invite code</label>")
             .append("<input id=\"code\" name=\"code\" required maxlength=\"16\" autocomplete=\"off\" autocapitalize=\"characters\" spellcheck=\"false\" aria-describedby=\"code-hint\">")
             .append("<p id=\"code-hint\" class=\"hint\">Six letters and numbers. Dashes and spaces are fine.</p>")
             .append("<button type=\"submit\">Continue</button></form>");
        } else {
            b.append("<p>Invites aren't open on this server right now.</p>");
        }
        if (signInOpen) b.append("<hr><p class=\"hint\">Already playing? Type <strong>/login</strong> in game to sign this browser in.</p>");
        return page(p.heading(), b.toString(), invitesOpen ? CODE_SCRIPT : null, p, false);
    }

    /** After a code that matches an open invite: whose it is, and nothing used up until they say yes. */
    static String confirm(PageSettings p, String csrf, String name, String code) {
        StringBuilder b = new StringBuilder(header(p));
        b.append("<p>This invite is for <strong>").append(esc(name)).append("</strong>.</p>")
         .append("<p>Is that your Minecraft username? Using the invite puts that name on the server's list. Only that account can join with it.</p>")
         .append("<form method=\"post\" action=\"").append(p.base()).append("/invite/confirm\">")
         .append("<input type=\"hidden\" name=\"csrf\" value=\"").append(esc(csrf)).append("\">")
         .append("<input type=\"hidden\" name=\"code\" value=\"").append(esc(code)).append("\">")
         .append("<button type=\"submit\">Yes, that's me</button></form>")
         .append("<p><a href=\"").append(p.base()).append("/\">No, go back</a></p>");
        return page(p.heading(), b.toString(), null, p, false);
    }

    /**
     * The signed-in view: the address to join and the first steps. This is the only page the play address is ever put
     * in, so it only reaches a browser with a valid session.
     */
    static String welcome(PageSettings p, String name, String csrf) {
        StringBuilder b = new StringBuilder(header(p));
        b.append("<p class=\"lede\">Welcome, ").append(esc(name)).append(".</p><p class=\"sub\">You're on the list.</p>");
        if (!p.playAddress().isEmpty()) {
            b.append("<section aria-labelledby=\"j\"><h2 id=\"j\">Join the server</h2>")
             .append("<p class=\"note\">Java Edition only. Bedrock can't join this server.</p>")
             .append("<ol class=\"num\" role=\"list\"><li>In Minecraft, choose <strong>Multiplayer</strong>, then <strong>Add Server</strong>.</li>")
             .append("<li>Paste this address into the <strong>Server Address</strong> box:")
             .append("<div class=\"addr\"><code id=\"addr\">").append(esc(p.playAddress())).append("</code>")
             .append("<button id=\"copy\" type=\"button\" aria-label=\"Copy server address\" hidden>Copy</button></div></li></ol></section>");
        }
        boolean steps = !p.steps().isEmpty(), map = !p.mapUrl().isEmpty();
        if (steps || map) {
            b.append("<div class=\"grid").append(steps && map ? "" : " one").append("\">");
            if (steps) {
                b.append("<section aria-labelledby=\"f\"><h2 id=\"f\">Your first time in</h2><ol class=\"num\" role=\"list\">");
                for (PageSettings.Step s : p.steps()) {
                    b.append("<li><strong>").append(esc(s.title())).append("</strong><br>").append(esc(s.text())).append("</li>");
                }
                b.append("</ol></section>");
            }
            if (map) {
                b.append("<section aria-labelledby=\"m\"><h2 id=\"m\">The map</h2>")
                 .append("<p>See the worlds you can enter and the players you're allowed to see. Opening it signs this browser in for you.</p>")
                 .append("<form method=\"post\" action=\"").append(p.base()).append("/map\"><input type=\"hidden\" name=\"csrf\" value=\"").append(esc(csrf))
                 .append("\"><button type=\"submit\">Open the map</button></form></section>");
            }
            b.append("</div>");
        }
        b.append("<footer><form method=\"post\" action=\"").append(p.base()).append("/signout\"><input type=\"hidden\" name=\"csrf\" value=\"").append(esc(csrf))
         .append("\"><button type=\"submit\" class=\"quiet\">Sign out of this browser</button></form></footer>");
        return page(p.heading(), b.toString(), COPY_SCRIPT, p, true);
    }

    static String signIn(PageSettings p, String csrf, boolean open, String msg, String msgKind) {
        StringBuilder b = new StringBuilder("<h1>Sign in</h1>");
        b.append(message(msg, msgKind));
        if (!open) {
            b.append("<p>Sign-in isn't open on this server right now.</p>");
            return page("Sign in", b.toString(), false);
        }
        b.append("<form method=\"post\" action=\"").append(p.base()).append("/signin\">")
         .append("<input type=\"hidden\" name=\"csrf\" value=\"").append(esc(csrf)).append("\">")
         .append("<label for=\"link\">Sign-in link</label>")
         .append("<input id=\"link\" name=\"link\" required maxlength=\"300\" autocomplete=\"off\" spellcheck=\"false\" aria-describedby=\"link-hint\">")
         .append("<p id=\"link-hint\" class=\"hint\">Filled in for you when you click the link in game. If it's empty, paste the link here.</p>")
         .append("<button type=\"submit\">Sign in</button></form>");
        return page("Sign in", b.toString(), true);
    }

    static String plain(String title, String text) {
        return page(title, "<h1>" + esc(title) + "</h1>" + message(text, ""), false);
    }

    static String sha256(String s) {
        try {
            return "sha256-" + Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(s.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
