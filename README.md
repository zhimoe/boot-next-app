# Boot-Next-App
fullstack app with spring boot and next.js app

features:
- export the next.js app as static pages for spring boot, no more Velocity or Thymeleaf
- access spring api directly in next.js app, no need config api url and port

## run
Run the application with one-liner:
```shell
mvn spring-boot:run
```
Then you can browse the website at:

http://localhost:8080/

## docs
1. create a spring boot application from start.spring.io, add following dependencies:
- lombok
- web
- devtools
2. add api controller named `DataEntryController`, run boot app with `mvn spring-boot`, 

now you can request api from shell:
```shell
curl http://localhost:8080/api/random-data
```

3. create a next.js app in project root folder named `frontend`
```shell
npx create-next-app@latest frontend --use-npm
```
config the next app to use [static exports](https://nextjs.org/docs/pages/building-your-application/deploying/static-exports)
```shell
# next.config.js
const nextConfig = {
    output: 'export', // static exports 
    reactStrictMode: true,
    swcMinify: true,
}
```
now you can run `npm run build` and see an `out` folder in `frontend`.

4. build next.js app with maven plugin and copy to spring boot target classpath
config your `pom.xml` with `frontend-maven-plugin` plugin, see the [frontend-maven-plugin doc](https://github.com/eirslett/frontend-maven-plugin#running-npm)

some notes:
- maven defines build as lifecycle with multi-phases. like `validate` `compile` `package`
- a Build Phase is Made Up of Plugin Goals
- a goal is responsible for a specific task
- which means you can add extra actions in each phase with your plugin.
- you can run maven command as `mvn <plugin | Goal Prefix>:<goal>` or `mvn <phase>`(default lifecycle), like `mvn compiler:testCompile` `mvn dependency:tree`
- `mvn help:describe -Dplugin=<groupid>:<artifactid> -Ddetail` to read doc of plugin

here in `frontend-maven-plugin` no need config phase cuz `goal:npm` is default bounded to `phase:generate-resources`

5. access url without `.html` extension
see `WebUrlExtensionConfig`

6. use spring boot api in next.js

TODO: