import pika
from smtplib import SMTP
import datetime
import json

SMTP_HOST = 'purkays.name'
SMTP_USERNAME = 'support@jobbifi.com'
SMTP_PORT = 25
SMTP_PASSWORD = 'lqC2QeMD9hRwjy4kfwc0'

def callback(ch, method, properties, body):
    print " [x] Received %r" % (body,)
    email_dict = json.loads(body)
    debuglevel = 0

    try:
        smtp = SMTP()
        smtp.set_debuglevel(debuglevel)
        smtp.connect(SMTP_HOST, SMTP_PORT)
        smtp.ehlo()
        smtp.starttls()
        smtp.ehlo()

    smtp.login(SMTP_USERNAME, SMTP_PASSWORD)

    from_addr = "<%s>" % (SMTP_USERNAME)
    to_addr = email_dict['recipient']

    subj = email_dict['subject']
    date = datetime.datetime.now().strftime( "%d/%m/%Y %H:%M" )

    message_text = email_dict['body']

    msg = "From: %s\nTo: %s\nSubject: %s\nDate: %s\n\n%s" % ( from_addr, to_addr, subj, date, message_text )

    smtp.sendmail(from_addr, to_addr, msg)
    smtp.quit()
    except Exception as e:
    print "Basic Exception:", str(e)

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='hello')

channel.basic_consume(callback,
                      queue='hello',
                      no_ack=True)
print ' [*] Waiting for messages. To exit press CTRL+C'
channel.start_consuming()