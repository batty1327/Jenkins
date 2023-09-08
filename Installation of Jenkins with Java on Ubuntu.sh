# Update the package list to get the latest information about available packages.
sudo apt update

# Install the OpenJDK 17 (Java Runtime Environment) package.
sudo apt install openjdk-17-jre

# Check the version of Java to verify that it has been installed successfully.
java -version

# The output shows the OpenJDK version and build information.

# Download the Jenkins GPG key and store it in the jenkins-keyring.asc file.
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

# Add the Jenkins repository to the system's package sources list.
# This repository is used to fetch Jenkins packages.
echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

# Update the package list again to include the Jenkins repository.
sudo apt-get update

# Install Jenkins from the newly added repository.
sudo apt-get install jenkins


# Updates the package list to ensure that it has the latest information about available packages.
# Installs the OpenJDK 17 (Java Runtime Environment).
# Checks the Java version to confirm the installation.
# Downloads the Jenkins GPG key and stores it in a file (jenkins-keyring.asc) for later verification.
# Adds the Jenkins repository to the list of sources used by APT (Advanced Package Tool) to fetch packages.
# Updates the package list again to include the Jenkins repository.
# Finally, it installs Jenkins from the newly added repository.