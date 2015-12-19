# -*- coding: utf-8 -*-
import emails
from emails.template import JinjaTemplate as T

import os
import sys, glob
sys.path.append('gen-py')
import mailer_pb2
pwd = os.path.dirname(os.path.realpath(__file__))

SMTP_HOST = 'purkays.name'
SMTP_USERNAME = 'support@jobbifi.com'
SMTP_SENDER_NAME = "Jobbifi Support"
SMTP_PORT = 25
SMTP_PASSWORD = 'lqC2QeMD9hRwjy4kfwc0'

templates = {}

logo_file = os.path.join(pwd, "logo.png")

m = emails.Message(html=T("""Hello {{ USER_NAME }},
 
Welcome and thanks for registering with Jobbifi!

You have successfully signed up as a {{ USER_TYPE }} on www.jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at <a href="mailto:support@jobbifi.com">support@jobbifi.com</a>.
 
Best regards
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},
 
Welcome and thanks for registering with Jobbifi!

You have successfully signed up as a {{ USER_TYPE }} on www.jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.
 
If you have any questions or encounter any problems logging in, please contact Jobbifi support at <a href="mailto:support@jobbifi.com">support@jobbifi.com</a>.
 
Best regards
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Welcome to Jobbifi"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_REGISTRATION] = m

###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},
You just requested a password reset for your login. 
Please choose one of the options below to reset your password.
Click on this link:
{{ PASSWORD_RESET_URL }}
OR
Copy and paste this URL into your browser’s address bar.
{{ PASSWORD_RESET_URL }}
The link will expire in 24 hours.
If you did not request a password reset, please ignore this email.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},
You just requested a password reset for your login. 
Please choose one of the options below to reset your password.
Click on this link:
{{ PASSWORD_RESET_URL }}
OR
Copy and paste this URL into your browser’s address bar.
{{ PASSWORD_RESET_URL }}
The link will expire in 24 hours.
If you did not request a password reset, please ignore this email.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Password reset on Jobbifi"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.FORGOT_PASSWORD] = m

###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Your password has been updated successfully.

Please login to www.Jobbifi.com with your updated password.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at support@jobbifi.com

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Your password has been updated successfully.

Please login to www.Jobbifi.com with your updated password.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at support@jobbifi.com

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Your Jobbifi Password has been changed"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.PASSWORD_UPDATED] = m

###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

A new mock interview {{ INTERVIEW_TITLE }} that matches your profile is available. You may place a bid to get awarded.

Please click on below link to view the mock interview.
{{ INTERVIEW_URL }}

We’ll continue to send you freshly published mock interviews we think are a good match with your profile.


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

A new mock interview {{ INTERVIEW_TITLE }} that matches your profile is available. You may place a bid to get awarded.

Please click on below link to view the mock interview.
{{ INTERVIEW_URL }}

We’ll continue to send you freshly published mock interviews we think are a good match with your profile.


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("A new mock interview matches your profile - {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_MOCK_INTERVIEW] = m

###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Please note that a mock interview you have placed a bid to, {{ INTERVIEW_TITLE }}, has been modified by the interviewee.

Please click on below link to view the changes made to the mock interview.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Please note that a mock interview you have placed a bid to, {{ INTERVIEW_TITLE }}, has been modified by the interviewee.

Please click on below link to view the changes made to the mock interview.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Recent Changes to Mock Interview - {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.MOCK_INTERVIEW_UPDATED] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Please note that a mock interview you have placed a bid to, {{ INTERVIEW_TITLE }}, has been cancelled by the interviewee.

We appreciate your participation in the Jobbifi’s marketplace.

Keep up the good work!


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Please note that a mock interview you have placed a bid to, {{ INTERVIEW_TITLE }}, has been cancelled by the interviewee.

We appreciate your participation in the Jobbifi’s marketplace.

Keep up the good work!


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("A mock interview you applied to was closed - {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.INTERVIEW_DELETED] = m

###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

you have received a new bid for your {{ INTERVIEW_TITLE }} mock interview.

The current highest bid is {{ BID_PRICE_HIGHEST }}.

Please click on below link to view the latest bids.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

you have received a new bid for your {{ INTERVIEW_TITLE }} mock interview.

The current highest bid is {{ BID_PRICE_HIGHEST }}.

Please click on below link to view the latest bids.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("You received a new bid for {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.BID_PLACED_INTERVIEWEE] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Your bid {{ BID_PRICE }} has been placed successfully for {{ INTERVIEW_TITLE }}.

The current highest bid is {{ BID_PRICE_HIGHEST }}.

Please click on below link to view the latest bids.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Your bid {{ BID_PRICE }} has been placed successfully for {{ INTERVIEW_TITLE }}.

The current highest bid is {{ BID_PRICE_HIGHEST }}.

Please click on below link to view the latest bids.
{{ INTERVIEW_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Your bid has been placed successfully for {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.BID_PLACED_INTERVIEWEE] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Congratulations! Your proposal of {{ BID_PRICE }} was chosen for {{ INTERVIEW_TITLE }}.

Reminder: You have agreed to receive all payments from Jobbifi clients using the Jobbifi Payment System as described in the Jobbifi Terms of Services (redirects to terms of services page). Please let us know at support@jobbifi.com if your client requests other payment arrangements.


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Congratulations! Your proposal of {{ BID_PRICE }} was chosen for {{ INTERVIEW_TITLE }}.

Reminder: You have agreed to receive all payments from Jobbifi clients using the Jobbifi Payment System as described in the Jobbifi Terms of Services (redirects to terms of services page). Please let us know at support@jobbifi.com if your client requests other payment arrangements.


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Congratulations! You won! - {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.AWARD_INTERVIEW_SUCCESS] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Your proposal to the {{ INTERVIEW_TITLE }} was declined.

Reason: Chose another Advisor

Keep Bidding!

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Your proposal to the {{ INTERVIEW_TITLE }} was declined.

Reason: Chose another Advisor

Keep Bidding!

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Proposal Ended - {{ INTERVIEW_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.INTERVIEW_BID_DENIED] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

{{ USER_NAME }} has just sent you a message.

You can view this conversation (hyperlink which should redirect user to appropriate message thread) and respond back.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

{{ USER_NAME }} has just sent you a message.

You can view this conversation (hyperlink which should redirect user to appropriate message thread) and respond back.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("You have a new message from {{ USER_NAME }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_MESSAGE] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

A new job {{{ JOB_TITLE }}} that matches your profile is available. You may send an application to get recruited.

Please click on below link to view the job details.
{{ JOB_URL }}

We’ll continue to send you freshly posted jobs we think are a good match with your profile.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

A new job {{{ JOB_TITLE }}} that matches your profile is available. You may send an application to get recruited.

Please click on below link to view the job details.
{{ JOB_URL }}

We’ll continue to send you freshly posted jobs we think are a good match with your profile.

Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("A new job posted matches your profile - {{ JOB_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_JOB] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

you have received a new job application for your {{ JOB_TITLE }} job.


Please click on below link to view the latest job application.
{{ JOB_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

you have received a new job application for your {{ JOB_TITLE }} job.


Please click on below link to view the latest job application.
{{ JOB_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("You’ve received a new job application for {{ JOB_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_JOB_APPLICATION] = m


###################################################################################################

m = emails.Message(html=T("""Hello {{ USER_NAME }},

Your profile has been shortlisted for {{ JOB_TITLE }} job you applied to.

Have patience, the employer may contact you soon.


Please click on below link to view the job details.
{{ JOB_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("""Hello {{ USER_NAME }},

Your profile has been shortlisted for {{ JOB_TITLE }} job you applied to.

Have patience, the employer may contact you soon.


Please click on below link to view the job details.
{{ JOB_URL }}


Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   subject=T("Your profile is shortlisted for {{ JOB_TITLE }}"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.JOB_APPLICATION_SHORTLISTED] = m


###################################################################################################

m = emails.Message(html=T("""Dear $user$,

	Your {{ INTERVIEW_TITLE }}
      has been marked in dispute by $disputer$. A detailed reason 
      for dispute is below:

      	{{ BID_MESSAGE }}


      The dispute id is $did$ that you can use in further communication 
      with us. The dispute will last for 4 days and a decision will be 
      taken on the last day. You have 4 days to present you 
      arguments and supportive document.
      Thanks,

      Mock Interview Team
"""),
                   text=T("""Dear $user$,

	Your {{ INTERVIEW_TITLE }}
      has been marked in dispute by $disputer$. A detailed reason 
      for dispute is below:

      	{{ BID_MESSAGE }}


      The dispute id is $did$ that you can use in further communication 
      with us. The dispute will last for 4 days and a decision will be 
      taken on the last day. You have 4 days to present you 
      arguments and supportive document.
      Thanks,

      Mock Interview Team
"""),
                   subject=T("A new dispute has been created"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename=logo_file, content_disposition="inline", data=open(logo_file, "rb"))

templates[mailer_pb2.NEW_DISPUTE] = m

