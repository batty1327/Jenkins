# Use the 'httpd' image as the base image
FROM httpd

# Set the working directory within the container to '/var/www/html/'
WORKDIR /var/www/html/

# Create an empty 'index.html' file
RUN touch index.html

# Add content to the 'index.html' file
RUN echo "THIS PROJECT IS DONE USING DOCKER IN JENKINS" > index.html

# Expose port 80 for incoming HTTP traffic (default for Apache)
EXPOSE 80

# Start the Apache HTTP Server in the foreground when a container is run
CMD ["httpd", "-d", "foreground;"]
