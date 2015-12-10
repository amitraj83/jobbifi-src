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

m = emails.Message(html=T("""Hello {{ SUPPORT_REQUEST_EMAIL }},
 
Welcome and thanks for registering with Jobbifi!

You have successfully signed up as a {Interviewee or Interviewer/Advisor} on www.Jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.

The following Email and password will be required to gain access to your application account.
 
Your email: {Email}
Your Password: {Password}

If you have any questions or encounter any problems logging in, please contact Jobbifi support at {Jobbifi support email address}
 
Best Regards, 
<img src='cid:logo.png' />
Jobbifi Support
"""),
                   text=T("Build passed: {{ project_name }} ..."),
                   subject=T("Welcome to Jobbifi"),
                   mail_from=(SMTP_SENDER_NAME, SMTP_USERNAME))
m.attach(filename="logo.png", content_disposition="inline", data=open("logo.png", "rb"))

templates[mailer_pb2.NEW_REGISTRATION_INTERVIEWER] = m