import pika
from smtplib import SMTP
import datetime
import json
import sys, glob
import os
pwd = os.path.dirname(os.path.realpath(__file__))
fn = os.path.join(pwd, "gen-py")
sys.path.append(fn)
import mailer_pb2
import requests
from templates import *
from jinja2 import Template as T

template_vars = {"USER_NAME":"Amit Raj", "MYNAME":"Aarav Raj"}

mailmap = {
mailer_pb2.NEW_REGISTRATION:T("""Hello {{ USER_NAME }},

Welcome and thanks for registering with Jobbifi! 

You have been successfully registrred on www.jobbifi.com, a unique online workplace that connects Job Seekers and Job Providers.

However, your account is not yet active. Please click on the following url to activate your account.

{{EMAIL_VARIFICATION_URL}}


If you have any questions or encounter any problems logging in, please contact Jobbifi support at support@jobbifi.com.

Best regards
Jobbifi Support
"""), 

mailer_pb2.FORGOT_PASSWORD:T("""Hello {{ USER_NAME }},

You just requested a password reset for your login. Please choose one of the options below to reset your password.


Click on this link:
{{ PASSWORD_RESET_URL }}

OR

Copy and paste this URL into your browser's address bar.
{{ PASSWORD_RESET_URL }}



The link will expire in 24 hours.

If you did not request a password reset, please ignore this email.

Best regards
Jobbifi Support
"""),
mailer_pb2.PASSWORD_UPDATED:T("""Hello {{ USER_NAME }},

Your password has been updated successfully.

Please login to www.Jobbifi.com with your updated password.

If you have any questions or encounter any problems logging in, please contact Jobbifi support at support@jobbifi.com

Best regards
Jobbifi Support
"""),
mailer_pb2.NEW_MESSAGE:T("""Hello {{ MESSAGE_RECEIVER }},

{{ MESSAGE_SENDER }} has just sent you a message.

You can view this conversation in the message section at https://www.jobbifi.com 

It is important to view your messages regularly and respond at your earliest. Your response might be very critical to others.

        Best regards<br />
        
        Jobbifi Support
</html></body>"""),
mailer_pb2.SUPPORT_REQUEST:T("""{{ SUPPORT_REQUEST_NAME }} &lt;{{ SUPPORT_REQUEST_EMAIL }}&gt;
      says
      
	  
      <pre>
      {{ SUPPORT_REQUEST_MESSAGE }}
      </pre>
     
        Best regards
        Jobbifi Support
      """)
}

subjectMap = {
mailer_pb2.NEW_REGISTRATION:"Welcome to Jobbifi",
mailer_pb2.FORGOT_PASSWORD:"Password reset on Jobbifi",
mailer_pb2.PASSWORD_UPDATED:"Your Jobbifi Password has been changed",
mailer_pb2.NEW_MESSAGE:"You have a new message on Jobbifi",
mailer_pb2.SUPPORT_REQUEST:"Support Request"
}

print mailmap[mailer_pb2.FORGOT_PASSWORD].render(template_vars)


def parse_email(email_message):
    email = mailer_pb2.Email()
    email.ParseFromString(email_message)
    return email

def send_simple_message(to, subject, text):
    return requests.post(
        "https://api.mailgun.net/v3/sandbox17ea25a222e444c6a58cffdb30dfbb2f.mailgun.org/messages",
        auth=("api", "key-419fd8f72a4f528389fc186bfffdb639"),
        data={"from": "Jobbifi Support <support@jobbifi.com>",
              "to": to,
              "subject": subject,
              "text": text})


def callback(ch, method, properties, body):
    print " [x] Received %r" % (body,)
    email = parse_email(body)
    emailData = {}
    for kv in email.data:
        emailData[mailer_pb2._ATTRIBUTETYPE.values_by_number[kv.type].name] = kv.value

    print "emailData: ", emailData
    try:
        #templates[email.type].send(render=emailData,
        #          to=email.recipient,
        #           smtp={"host": "server15.hostingraja.in", "port": 465, 'ssl': True, 'user': 'support@jobbifi.com', 'password': 'password123'})
        
	send_simple_message(email.recipient, subjectMap[email.type], mailmap[email.type].render(emailData))
	print "[INFO] Sent mail to %s" % (email.recipient) % "  %s " %(mailmap[email.type].render(emailData))
    except Exception as e:
        print "Basic Exception:", str(e)
        import traceback
        traceback.print_exc()

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='hello')

channel.basic_consume(callback,
                      queue='hello',
                      no_ack=True)
print ' [*] Waiting for messages. To exit press CTRL+C'
channel.start_consuming()
