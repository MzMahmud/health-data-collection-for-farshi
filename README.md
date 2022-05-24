# Health Data Collection for Farshi

First of all don't be alarmed by the lack of any code besides only a `README.md` file! It is done on purpose! Let explain:

This is a project mainly for Umme Sabiha Farshi, my wife. She is working as a Nutritionist in a health check-up compaign. This project helps her to prescribe the patients with proper diet. Apart from that, these data is also needed for her research. I was looking for off the shelf solution like `google form`. But google form does not support realtime calculation of data. There is a paid add-on for realtime calculation name [`formcade`](https://formfacade.com/website/how-to-add-a-calculated-field-in-google-form-on-1FAIpQLSeooiyPfimYtFV-LlN3uiXUNu3QbPzVxCa33BCSGrPR2AqwcA.html). Though they have a free trial, I was unable to find the amount of data and limitations for the trial. So it was unusable!

In this situation, this project comes to my mind. This project is a simple data entry form with runtime calculation. Now lets come to the lack of code! The following subsection list some branch names that has the codes.

### `vanilla-js-local-storage/main`
As the name suggests, this branch contains a simple `vanilla js` implementation of the project. This is the first version I made. This version uses the browsers `local storage` as a database. The raw data can be downloaded as `json` and `csv` files. As this uses local storage, the data has to be combined manually. But it is not a problem because of a single user!

Farshi used this for one day. The data is backed up and stored.

### `spring-boot-postgrace/main`
As the name suggests, this branch contains an implementation with `spring boot` as backend and `postgrace` as database. `thymeleaf` is used as the templating engine with spring boot. Postgrace is used as I wanted to deploy to a free tier in `heroku`. This project also supports raw download as `json`.

I deployed this project to heroku using `heroku cli`. I could not use heroku's `github` auto deploy feature because at this time, heroku's github integration api was down.

This version is deployed at [`health-data-collection.herokuapp.com`](http://health-data-collection.herokuapp.com/)

### Rest of the git branches
- `main` contains the `README.md` file you are reading.
- `master` is needed to auto build and deploy in heroku cli.

Other branches are `feature branch`. 