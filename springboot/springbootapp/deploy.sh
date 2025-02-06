read -p "Enter path to project: " path_to_project
read -p "Enter path to private SSH key: " path_to_ssh_key

cd $path_to_project
mvn clean package
if [ $? -ne 0 ]; then
  echo "Maven build failed. Exiting."
  exit 1
fi

scp -i $path_to_ssh_key -P 45275 -r /tmp/test/springApp-exec.jar boyang@45.133.101.154:/tmp
if [ $? -ne 0 ]; then
  echo "SCP transfer failed. Exiting."
  exit 1
fi

ssh -i $path_to_ssh_key -p 45275 boyang@45.133.101.154
if [ $? -ne 0 ]; then
  echo "SSH connection failed. Exiting."
  exit 1
fi
