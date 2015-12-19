import emails
from emails.template import JinjaTemplate as T

import sys, glob
sys.path.append('gen-py')
import mailer_pb2

SMTP_HOST = 'purkays.name'
SMTP_USERNAME = 'support@jobbifi.com'
SMTP_SENDER_NAME = "Jobbifi Support"
SMTP_PORT = 25
SMTP_PASSWORD = 'lqC2QeMD9hRwjy4kfwc0'

templates = {}

m = emails.Message(html=T("""Hello {{ USER_NAME }},
 
Welcome and thanks for registering with Jobbifi!

You have successfully signed up as a {{ USER_TYPE }} on www.jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.

The following Email and password will be required to gain access to your application account.
 
Your email: {Email}

If you have any questions or encounter any problems logging in, please contact Jobbifi support at <a href="mailto:support@jobbifi.com">support@jobbifi.com</a>.
 
Best regards
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},
 
Welcome and thanks for registering with Jobbifi!

You have successfully signed up as a {{ USER_TYPE }} on www.jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.

The following Email and password will be required to gain access to your application account.
 
Your email: {Email}

If you have any questions or encounter any problems logging in, please contact Jobbifi support at <a href="mailto:support@jobbifi.com">support@jobbifi.com</a>.
 
Best regards
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Welcome to Jobbifi"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_REGISTRATION] = m

###################################################################################################

m = emails.Message(html=T("""Hello {Username},
You just requested a password reset for your login. 
Please choose one of the options below to reset your password.
Click on this link:
{Reset Password URL}
OR
Copy and paste this URL into your browser’s address bar.
{Reset Password URL}
The link will expire in 24 hours.
If you did not request a password reset, please ignore this email.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},
You just requested a password reset for your login. 
Please choose one of the options below to reset your password.
Click on this link:
{Reset Password URL}
OR
Copy and paste this URL into your browser’s address bar.
{Reset Password URL}
The link will expire in 24 hours.
If you did not request a password reset, please ignore this email.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Password reset on Jobbifi"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.FORGOT_PASSWORD] = m

###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Your password has been reset successfully.

Your new password is {New Password}

Please login to www.Jobbifi.com with your updated password.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at {Jobbifi support email address}

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Your password has been reset successfully.

Your new password is {New Password}

Please login to www.Jobbifi.com with your updated password.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at {Jobbifi support email address}

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Your Jobbifi Password has been changed"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.PASSWORD_UPDATED] = m

###################################################################################################

m = emails.Message(html=T("""Hello {Username},

A new mock interview {Mock Interview Title_should be displayed with hyperlink} that matches your profile is available. You may place a bid to get awarded.

Please click on below link to view the mock interview.
{Mock Interview details page URL}

We’ll continue to send you freshly published mock interviews we think are a good match with your profile.


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

A new mock interview {Mock Interview Title_should be displayed with hyperlink} that matches your profile is available. You may place a bid to get awarded.

Please click on below link to view the mock interview.
{Mock Interview details page URL}

We’ll continue to send you freshly published mock interviews we think are a good match with your profile.


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("A new mock interview matches your profile - {Mock Interview Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_MOCK_INTERVIEW] = m

###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Please note that a mock interview you have placed a bid to, {Mock Interview Title_should be displayed with hyperlink}, has been modified by the interviewee.

Please click on below link to view the changes made to the mock interview.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Please note that a mock interview you have placed a bid to, {Mock Interview Title_should be displayed with hyperlink}, has been modified by the interviewee.

Please click on below link to view the changes made to the mock interview.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Recent Changes to Mock Interview - {Mock Interview Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.MOCK_INTERVIEW_UPDATED] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Please note that a mock interview you have placed a bid to, {Interview Title}, has been cancelled by the interviewee.

We appreciate your participation in the Jobbifi’s marketplace.

Keep up the good work!


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Please note that a mock interview you have placed a bid to, {Interview Title}, has been cancelled by the interviewee.

We appreciate your participation in the Jobbifi’s marketplace.

Keep up the good work!


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("A mock interview you applied to was closed - {Mock Interview Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.MOCK_INTERVIEW_DELETED] = m

###################################################################################################

m = emails.Message(html=T("""Hello {Username},

you have received a new bid for your {Mock Interview Title_should be displayed with hyperlink} mock interview.

The current highest bid is {Highest bid amount}.

Please click on below link to view the latest bids.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

you have received a new bid for your {Mock Interview Title_should be displayed with hyperlink} mock interview.

The current highest bid is {Highest bid amount}.

Please click on below link to view the latest bids.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("You received a new bid for {mock interview title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.BID_PLACED_FOR_INTERVIEWEE] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Your bid {bid amount} has been placed successfully for {Mock Interview Title_should be displayed with hyperlink}.

The current highest bid is {Highest bid amount}.

Please click on below link to view the latest bids.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Your bid {bid amount} has been placed successfully for {Mock Interview Title_should be displayed with hyperlink}.

The current highest bid is {Highest bid amount}.

Please click on below link to view the latest bids.
{Mock Interview details page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Your bid has been placed successfully for {mock interview title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.BID_PLACED_FOR_INTERVIEWER] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Congratulations! Your proposal of {bid amount} was chosen for {Mock Interview Title_should be displayed with hyperlink}.

Reminder: You have agreed to receive all payments from Jobbifi clients using the Jobbifi Payment System as described in the Jobbifi Terms of Services (redirects to terms of services page). Please let us know at {Jobbifi support email address} if your client requests other payment arrangements.


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Congratulations! Your proposal of {bid amount} was chosen for {Mock Interview Title_should be displayed with hyperlink}.

Reminder: You have agreed to receive all payments from Jobbifi clients using the Jobbifi Payment System as described in the Jobbifi Terms of Services (redirects to terms of services page). Please let us know at {Jobbifi support email address} if your client requests other payment arrangements.


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Congratulations! You won! - {Mock Interview Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.INTERVIEW_AWARDED] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Your proposal to the {Mock Interview Title_should be displayed with hyperlink} was declined.

Reason : Chose another Advisor

Keep Bidding!

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Your proposal to the {Mock Interview Title_should be displayed with hyperlink} was declined.

Reason : Chose another Advisor

Keep Bidding!

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Proposal Ended - {Mock Interview Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.INTERVIEW_BID_DENIED] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

{Message sender username} has just sent you a message.

You can view this conversation (hyperlink which should redirect user to appropriate message thread) and respond back.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

{Message sender username} has just sent you a message.

You can view this conversation (hyperlink which should redirect user to appropriate message thread) and respond back.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("You have a new message from {Message sender username}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_MESSAGE] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

A new job {{Job Title_should be displayed with hyperlink}} that matches your profile is available. You may send an application to get recruited.

Please click on below link to view the job details.
{Job detail page URL}

We’ll continue to send you freshly posted jobs we think are a good match with your profile.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

A new job {{Job Title_should be displayed with hyperlink}} that matches your profile is available. You may send an application to get recruited.

Please click on below link to view the job details.
{Job detail page URL}

We’ll continue to send you freshly posted jobs we think are a good match with your profile.

Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("A new job posted matches your profile - {Job Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_JOB] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

you have received a new job application for your {Job Title_should be displayed with hyperlink} job.


Please click on below link to view the latest job application.
{Job Application page URL_Clicking on this link, job should be expanded automatically showing new job applications, you might need to take care of scrolling of the page where the job was posted in the list.}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

you have received a new job application for your {Job Title_should be displayed with hyperlink} job.


Please click on below link to view the latest job application.
{Job Application page URL_Clicking on this link, job should be expanded automatically showing new job applications, you might need to take care of scrolling of the page where the job was posted in the list.}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("You’ve received a new job application for {Job Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_JOB_APPLICATION] = m


###################################################################################################

m = emails.Message(html=T("""Hello {Username},

Your profile has been shortlisted for {Job Title_should be displayed with hyperlink} job you applied to.

Have patience, the employer may contact you soon.


Please click on below link to view the job details.
{Job detail page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   text=T("""Hello {Username},

Your profile has been shortlisted for {Job Title_should be displayed with hyperlink} job you applied to.

Have patience, the employer may contact you soon.


Please click on below link to view the job details.
{Job detail page URL}


Best Regards, 
{Company Logo}
Jobbifi Support
"""),
                   subject=T("Your profile is shortlisted for {Job Title}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.JOB_APPLICATION_SHORTLISTED] = m


###################################################################################################

m = emails.Message(html=T("""Dear $user$,

	Your interview $ititle$
      has been marked in dispute by $disputer$. A detailed reason 
      for dispute is below:

      	$message$


      The dispute id is $did$ that you can use in further communication 
      with us. The dispute will last for 4 days and a decision will be 
      taken on the last day. You have 4 days to present you 
      arguments and supportive document.
      Thanks,

      Mock Interview Team
"""),
                   text=T("""Dear $user$,

	Your interview $ititle$
      has been marked in dispute by $disputer$. A detailed reason 
      for dispute is below:

      	$message$


      The dispute id is $did$ that you can use in further communication 
      with us. The dispute will last for 4 days and a decision will be 
      taken on the last day. You have 4 days to present you 
      arguments and supportive document.
      Thanks,

      Mock Interview Team
"""),
                   subject=T("A new dispute has been created"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_DISPUTE] = m

