#! /bin/bash

#
# This script will download and install tomee
# in the user home executing the command
#

# "exposed" variables
INSTALL_DIR=~/apache-tomee
TOMEE_VERSION=7.0.0-M3
TOMEE_CLASSIFIER=webprofile

set -e
set -u

# a single stream is enough
exec 1>&2

# Find Java
if type -p java; then
    _java=java
elif [ -n "$JAVA_HOME" -a -x "$JAVA_HOME/bin/java" ];  then   
    _java="$JAVA_HOME/bin/java"
else
    echo "Java not found . Please install java. Aborting"
    exit 1
fi

# Targets JavaEE 7 so java 7 at least
if [ "$_java" ]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [ "$version" \< "1.7" ]; then           
        echo "JDK Version is less than 1.7 . TomEE $TOMEE_VERSION requires JDK 7+ . Aborting."
        exit 1
    fi
fi

TMP_DIR="$INSTALL_DIR/install"
TMP_CONF_DIR="$INSTALL_DIR/install_conf"
TMP_WEBAPPS_DIR="$INSTALL_DIR/install_webapps"
TMP_APPS_DIR="$INSTALL_DIR/install_apps"

# save existing configuration
if [ -d "$INSTALL_DIR" ]; then
    echo "Saving previous installation state."

    rm -rf "$TMP_CONF_DIR" "$TMP_WEBAPPS_DIR" "$TMP_APPS_DIR"
    mkdir -p "$TMP_CONF_DIR" "$TMP_WEBAPPS_DIR" "$TMP_APPS_DIR"

    tomee_base="$INSTALL_DIR/apache-tomee-$TOMEE_CLASSIFIER-$TOMEE_VERSION"
    cp -r "$tomee_base/conf/." "$TMP_CONF_DIR"
    cp -r "$tomee_base/webapps/." "$TMP_WEBAPPS_DIR"
    if [ -d "$tomee_base/apps" ]; then
        cp -r "$tomee_base/apps/." "$TMP_APPS_DIR"
    fi

    # remove distribution webapp to use new ones
    rm -rf "$TMP_WEBAPPS_DIR/ROOT" "$TMP_WEBAPPS_DIR/docs" "$TMP_WEBAPPS_DIR/manager" "$TMP_WEBAPPS_DIR/host-manager"
fi

# get the new binaries
rm -rf "$INSTALL_DIR" "$TMP_DIR"
mkdir -p "$INSTALL_DIR" "$TMP_DIR"

DOWNLOAD_LINK="http://repo.maven.apache.org/maven2/org/apache/tomee/apache-tomee/$TOMEE_VERSION/apache-tomee-$TOMEE_VERSION-$TOMEE_CLASSIFIER.zip"
DOWNLOAD_ZIP="$TMP_DIR/tomee.zip"
echo "Downloading TomEE $TOMEE_VERSION from $DOWNLOAD_LINK"
curl --location --fail --progress-bar $DOWNLOAD_LINK > "$DOWNLOAD_ZIP"

test -f "$DOWNLOAD_ZIP"

echo "Extracting TomEE $TOMEE_VERSION"
unzip "$DOWNLOAD_ZIP" -d "$INSTALL_DIR"
TOMEE_BASE="$INSTALL_DIR/apache-tomee-$TOMEE_CLASSIFIER-$TOMEE_VERSION"
test -x "$TOMEE_BASE"

# restore configuration
if [ -d "$TMP_CONF_DIR" ]; then
    echo "Restoring previous installation state."
    cp -r "$TMP_CONF_DIR" "$TOMEE_BASE/conf"
    cp -r "$TMP_WEBAPPS_DIR" "$TOMEE_BASE/webapps"
    cp -r "$TMP_APPS_DIR" "$TOMEE_BASE/apps"
    rm -rf "$TMP_CONF_DIR" "$TMP_WEBAPPS_DIR" "$TMP_APPS_DIR"
else
    echo "No state to restore"
fi
rm -rf "$TMP_DIR"

echo "TomEE $TOMEE_VERSION is now installed in directory $TOMEE_BASE."

# create the profile script (vars)
TOMEE_PROFILE=~/.tomee.profile
if [ -f "$TOMEE_PROFILE" ]; then
    rm -rf "$TOMEE_PROFILE"
fi
touch "$TOMEE_PROFILE"
echo "export CATALINA_HOME="$TOMEE_BASE"" >> "$TOMEE_PROFILE"
echo "export CATALINA_BASE="$TOMEE_BASE"" >> "$TOMEE_PROFILE"
echo "# To add tomcat scripts to the path uncomment next line" >> "$TOMEE_PROFILE"
echo "# export PATH="\$PATH:\$CATALINA_HOME/bin"" >> "$TOMEE_PROFILE"
# some alternative commands, mainly to limit side effects of cygwin (wrong paths)
echo "alias tomee-base='cd $TOMEE_BASE'" >> "$TOMEE_PROFILE"
echo "alias tomee-run='cd $TOMEE_BASE && ./bin/catalina.sh run && cd -'" >> "$TOMEE_PROFILE"
echo "alias tomee-start='cd $TOMEE_BASE && ./bin/catalina.sh start && cd -'" >> "$TOMEE_PROFILE"
echo "alias tomee-stop='cd $TOMEE_BASE && ./bin/catalina.sh stop && cd -'" >> "$TOMEE_PROFILE"

# make it immediate
source "$TOMEE_PROFILE"

# add it to the profile file whatever it is,
# if they source themself it would reload env variable which is ok
for f in ".bash_profile" ".bashrc" ".profile"; do
    if [ -f ~/$f ];then
        if grep "$TOMEE_PROFILE" ~/$f > /dev/null; then
           echo "$TOMEE_PROFILE already sourced in $f"
        else
            echo source "$TOMEE_PROFILE" >> ~/$f
        fi
    fi
done

# Finally dump a small getting started
echo ""
echo ""
echo "Get started:"
echo "------------"
echo ""
echo ""
echo "start in blocking mode TomEE - located in $TOMEE_BASE"
echo "me@local:~ $ tomee-run"
echo "..."
echo "Ctrl+C"
echo ""
echo "start and forget - located in $TOMEE_BASE"
echo "me@local:~ $ tomee-start"
echo ""
echo "..."
echo "And stop it"
echo "me@local:~ $ tomee-stop"
echo ""
echo " To deploy quickly an application drop it in $TOMEE_BASE/webapps and restart."
echo "You can configure TomEE in $TOMEE_BASE/conf/ folder or in ~/.openejb/system.properties."
echo ""
echo "You can use tomee-base command to directly go in $TOMEE_BASE"
echo ""
echo "Learn more on http://tomee.apache.org"
echo ""
