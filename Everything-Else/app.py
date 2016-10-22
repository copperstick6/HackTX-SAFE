from flask import Flask, render_template, request, flash, redirect, url_for
import flask_login
from flask_pymongo import PyMongo
from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField, SelectField, validators, ValidationError
from werkzeug.security import generate_password_hash, check_password_hash
from twilio.rest import TwilioRestClient
from flask_mail import Mail, Message
from flask_security import login_required
import random
import keys
import sys

# method to check if the email is unique
def unique_email(form, field):
	print(field.data, file=sys.stderr)
	result = mongo.db.users.find_one({'email': field.data})
	if result is not None:
		raise ValidationError('Email already exists')

# validator to check if phone number is required
def required_if_using_text(form, field):
	if dict([('email', 'Email'), ('text', 'Text Message')]).get(form.verify_method.data) == 'text':
		return validators.DataRequired('Phone Number is required')

# The signup form
# feel free to change the string values to something better
# TODO check about .data vs w/ out in selectfield
class RegisterForm(FlaskForm):
	# the verifcation method choices
	verify_choices = [('email', 'Email'), ('text', 'Text Message')]
	# all the fields
	first_name = StringField('First Name', [validators.DataRequired('First Name is required')])
	last_name = StringField('Last Name', [validators.DataRequired('Last Name is required')])
	email = StringField('Email', [validators.DataRequired('Email is required'), validators.Email(), unique_email])
	password = PasswordField('Password', [validators.DataRequired('Password is required'),
		validators.EqualTo('repeat_password', 'Passwords are not the same')])
	repeat_password = PasswordField('Repeat Password', [validators.DataRequired('Must repeat password')])
	verify_method = SelectField('Chose Verification Method', choices=verify_choices)
	phone_number = StringField('Phone Number', [required_if_using_text])
	submit = SubmitField('Sign Up')

# The signup form
# Again, feel free to change the label values
class LoginForm(FlaskForm):
	email = StringField('Email', [validators.DataRequired('Email is required')])
	password = PasswordField('Password', [validators.DataRequired('Password is required')])
	submit = SubmitField('Login')

# User class (used for logging in)
class User(flask_login.UserMixin):
	pass

# set up the app
app = Flask(__name__)
app.secret_key = keys.app_secret()

# set up the login manager
login_manager = flask_login.LoginManager()
login_manager.init_app(app)

# mongo setup
app.config['MONGO_DBNAME'] = 'safe_db'
mongo = PyMongo(app)

# email stuff
gmail_username = 'safe2faconfirm@gmail.com'
# TODO turn off DEBUG when in production
app.config.update(
	DEBUG = True,
	MAIL_SERVER = 'smtp.gmail.com',
	MAIL_PORT = 465,
	MAIL_USE_SSL = True,
	MAIL_USERNAME = gmail_username,
	MAIL_PASSWORD = keys.gmail_password()
	)
mail = Mail(app)

# flashes form errors
def flash_errors(form):
	for field, errors in form.errors.items():
		for err in errors:
			flash("Error in the %s field: %s" % (getattr(form, field).label.text, err))

# the home page
@app.route('/')
def index():
	return render_template('index.html')

# the signup page
@app.route('/signup', methods=['GET', 'POST'])
def signup():
	form = RegisterForm(request.form)
	if request.method == 'POST' and form.validate():
		email = request.form['email']
		first = request.form['first_name']
		last = request.form['last_name']
		phone_number = request.form['phone_number']
		passwordHash = generate_password_hash(request.form['password'])
		verify_method = form['verify_method'].data
		print("VERIFICATION METHOD:", verify_method)
		unique_code = random.randint(10000, 99999)
		
		# send verification msg
		if verify_method == 'email':
			send_verification_email(unique_code, email)
		elif verify_method == 'text':
			send_verification_text(unique_code, phone_number)
		#place user in database
		mongo.db.users.insert_one({'email': email, 'password': passwordHash, 'verify_code': unique_code,
			'first': first, 'last': last})
		return redirect(url_for('index'))
	elif request.method == 'POST':
		flash_errors(form)
	return render_template('signup.html', form = form)

# send verification text
def send_verification_text(unique_code, address):
	print("TEXTING", file=sys.stderr)
	client = TwilioRestClient(keys.twilioSSIDKey(), keys.twilioAuth())
	msg_body = "Your verification code is: " + str(unique_code)
	message = client.messages.create(body = msg_body,
		to = address,
		from_ = keys.phoneNumber())

# send verification email
def send_verification_email(unique_code, address):
	print("EMAILING", file=sys.stderr)
	print("ADDRESS:", address)
	msg_body = "Your verification code is: " + str(unique_code)
	msg = Message('Verification Code', sender = gmail_username, recipients = [address])
	msg.body = msg_body
	mail.send(msg)

@app.route('/login', methods=['GET', 'POST'])
def login():
	form = LoginForm(request.form)
	if request.method == 'POST' and form.validate():
		email = request.form['email']
		userRecord = mongo.db.users.find_one({'email': email})
		if userRecord is not None:
			user = User()
			user.id = email
			flask_login.login_user(user)
			return redirect(url_for('index'))
	elif request.method == 'POST':
		flash_errors(form)
	return render_template('login.html', form = form)

@login_manager.user_loader
def load_user(email):
	userRecord = mongo.db.users.find_one({'email': email})
	# if no user w/ this email exists, return None
	if userRecord is None:
		return
	user = User()
	user.id = email
	return user

@login_manager.request_loader
def request_loader(request):
	email = request.form.get('email')
	userRecord = mongo.db.users.find_one({'email': email})
	# if no user w/ this email exists, return None
	if userRecord is None:
		return
	user = User()
	user.id = email
	passwordHash = request.form['password']
	user.is_authenticated = check_password_hash(passwordHash, password)
	return user

@app.route('/logout')
def logout():
	flask_login.logout_user()
	return redirect(url_for('index'))

@app.route('/settings')
@login_required
def settings():
	return flask_login.current_user.id

if __name__ == '__main__':
	app.run()
