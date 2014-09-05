amazon-kinesis-jython
=====================

An Amazon Kinesis consumer that passes record data to a custom Jython-based processor.


## Configuration

### Specify your AWS credentials

Copy the credentials file to ~/.aws/credentials and edit with your access and secret keys.


### Set your application settings for stream name and the python module that you want to process Kinesis Record data

Copy the application.properties.template to application.properties and fill in your stream and python module paths.

Note: multiple of these property files can be created.  See run.sh for how they are passed in at runtime.


### Set the PYTHONPATH that is used by the Jython PythonInterpreter.

Simply change the PYTHONPATH value in IPythonProcessorFactory to ensure any modules in your Jython code will be available to import.

Note: this is different than any PYTHONPATH settings on your host machine.


## Building

Build command:

```
ant
```

## Running the Application

```
./run.sh
```

Note: the default setup will count the events passed to the PythonProcessor and echo back their JSON.


## A note on Kinesis message format

The JythonRecordProcessor implementation presumes that Kinesis messages are zlib compressed JSON events (individual events or an array of events).


## A note on Jython module support

Modules that are pure-Python seem to work as expected.

In testing, I found a few modules (like json & numpy) that threw quite a few exceptions, most likely because they were calling out to C extensions.

This certainly limits the usability of this method of consuming Kinesis events in python, but it still works fairly well.


## Performance

In testing against a two shard Kinesis stream and over terribly slow internet/WiFi, two "no-op" consumers handled about 40k messages per minute.

