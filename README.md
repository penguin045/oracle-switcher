# Oracle Switcher

This project uses **Scala 3** with **Scala.js** to target the browser.

## Build

Use [sbt](https://www.scala-sbt.org/) to compile the project:

```bash
sbt fastLinkJS
```

The generated JavaScript will be found under `target/scala-3.*`. You can include it in an HTML page using a `<script type="module">` tag.

## Development Notes

- A `package.json` and bundler configuration can be added later if you want to manage npm dependencies or bundle assets.
- Scala.js already outputs an ES module that can be used directly with modern browsers or further processed by tools like webpack or Vite.
