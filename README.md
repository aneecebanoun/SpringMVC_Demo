[![CircleCI](https://circleci.com/gh/aneecebanoun/SpringMVC_Demo.svg?style=svg)](https://circleci.com/gh/aneecebanoun/SpringMVC_Demo)


[![codebeat badge](https://codebeat.co/badges/5fe511ae-45f4-42e5-9146-5eaa7fcb13e9)](https://codebeat.co/projects/github-com-aneecebanoun-springmvc_demo-master)


[![Codacy Badge](https://api.codacy.com/project/badge/Grade/839324d80bb34b09964c67ae85f32750)](https://www.codacy.com/app/java2ee5/SpringMVC_Demo?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=aneecebanoun/SpringMVC_Demo&amp;utm_campaign=Badge_Grade)

PLEASE refer to Requirements.pdf

After downloading and extracting the project:
-import the project to STS as maven project
-Right click on project go to run as and select Maven build and type package in Goal field
-Right click on project go run as and select String Boot App

The app should run in embedded tomcat and is accessible through:
http://localhost:9090/productSelection/1
for london customer 
http://localhost:9090/productSelection/2
for liverpool customer 
http://localhost:9090/productSelection/3 ---> any other number
for no match customer 

=================================================

ADDED Web Functionality from StockTrading project

=================================================

These are 2 controllers & 2 jsps to display traditional sql table as displayed in CLI but in the browser using String.format
to access use:
http://localhost:9090/stockReporting
you may navigate to each row by clicking on values in the Entity column
you may sort by clicking on table header the next click will flip sorting order
when you come back to the page last sorting order maintained using session scope 

