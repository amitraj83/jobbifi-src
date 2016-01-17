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

    print "emailData: ", emailData
    try:
        templates[email.type].send(render=emailData,
                  to=email.recipient,
                   smtp={"host": "server15.hostingraja.in", "port": 465, 'ssl': True, 'user': 'support@jobbifi.com', 'password': 'password123'})
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
