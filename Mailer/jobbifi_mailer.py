import pika
from smtplib import SMTP
import datetime
import json
import sys, glob
sys.path.append('gen-py')
import mailer_pb2

from templates import *

def parse_email(email_message):
    email = mailer_pb2.Email()
    email.ParseFromString(email_message)
    return email

def callback(ch, method, properties, body):
    print " [x] Received %r" % (body,)
    email = parse_email(body)
    emailData = {}
    for kv in email.data:
        emailData[mailer_pb2._ATTRIBUTETYPE.values_by_number[kv.type].name] = kv.value

    try:
        templates[email.type].send(render=emailData,
                  to=email.recipient,
                  smtp={"host": SMTP_HOST, "port": SMTP_PORT})
        print "[INFO] Sent mail to %s" % (email.recipient)
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
