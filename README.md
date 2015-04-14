#cs3050 group-project
A big project for cs3050. Full requirements found [here](https://github.com/PDirks/cs3050-fun-group-project/blob/master/requirements/guidelines.pdf)

###Prompt
You have to find a way of allocating each job applicant to at most one department in such a way that all the vacancies
in all the departments shall be filled. Note that since there are more job applicants than the number of vacancies, there would some job applicants who do not get a job.

Well-formed is defined as when the following doesn't happen:

1.There are two job applicants A 1 and A 2 and a department D such that
* A 1 is allocated to D, and
* A 2 is not allocated to any department, and
* D prefers A 2 to A 1

2.There are two job applicants A 1 and A 2 and two departments D 1 and D 2 such that
* A 1 is allocated to D 1 , and
* A 2 is allocated to D 2 , and
* D 1 prefers A 2 to A 1 and
* A 2 prefers D 1 to D 2 .

###General program flow

![](https://raw.githubusercontent.com/PDirks/cs3050-fun-group-project/master/requirements/3050-activity.png?token=AGaMKSTdS1-7rO7-sIHhfLKO72SaNaj9ks5VJqGcwA%3D%3D)

Algorithm magic will resemble the ~~[stable marriage algorithm](http://en.wikipedia.org/wiki/Stable_marriage_problem#Algorithm)~~ 

[hospital/resident program](http://www.nrmp.org/wp-content/uploads/2014/05/Run-A-Match.pdf)

###Input Layout
* Each Input file starts with the keyword “Vacancies and Departments” in the first line. The next line is blank.
The next few lines will contain the list of departments and the vacancies, which are specified as follows. Each
line contains exactly information of only one department. The line will first contain the number of vacancies in a
department and then the name of the department. Note that a number is any sequence of digits. The department
name can consist of letters from the English Alphabet, underscores and spaces.
The listing of departments and vacancies ends with a blank line.
* After the listing of the departments and vacancies, the listing of job applicants begin with the keyword “Job
Applicants.” The next line is blank.
The next few lines will contain the list of job applicants. Each line will list exactly one job applicant. The name of
a job applicant can consist of letters from the English Alphabet and spaces. The listing of job applicants ends with
a blank line.
* After the listing of job applicants, we will start listing the preferences of each department. Preferences of department
named D starts with the line “Preferences D.” The line after this will be blank.
The next few lines will list the job applicants in decreasing order of preference. Note that each line contains the
name of one job applicant only. The listing of preferences ends with a blank line.
Furthermore, if department D 1 is listed before department D 2 in “Vacancies and Departments” then preferences of
D 1 must be listed before preferences of D 2.
* After the listing of preferences of each department, we will list the preferences of each applicant. Preferences of
applicant named A starts with the line “Preferences A.” The line after this will be blank.
The next few lines will list the departments in decreasing order of preference. Note that each line contains the name
of one department only. The listing of preferences of “A” ends with a blank line.
Furthermore, if applicant A 1 is listed before applicant A 2 in “Job Applicants” then preferences of A 1 must be listed
before preferences of A 2 .
* The input ends with a line with the keyword “END INPUT.”
* sample file found [here](https://raw.githubusercontent.com/PDirks/cs3050-fun-group-project/master/requirements/test.txt?token=AGaMKe7HYRC7tKU8kFC3W7oavtjJO_PXks5VJqKWwA%3D%3D)
