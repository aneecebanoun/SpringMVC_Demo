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

