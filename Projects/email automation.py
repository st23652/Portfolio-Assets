import smtplib
from email.mime.text import MIMEText

smtp_server = 'smtp.gmail.com'
smtp_port = 587
smtp_username = '5688sneha8865@gmail.com'
smtp_password = 'happy@2577' 

from_mail = smtp_username
to_mail = 'htre@eim.ae'
subject = 'Introduction: Test Mail'

message = MIMEText(f'This is the email body.', 'plain')
message['Subject'] = subject
message['From'] = from_mail
message['To'] = to_mail

with smtplib.SMTP(smtp_server, smtp_port) as smtp:
    smtp.ehlo()
    smtp.starttls()
    smtp.ehlo()
    smtp.login(smtp_username, smtp_password)
    smtp.sendmail(from_mail, to_mail, message.as_string())

print('Email sent successfully!')
