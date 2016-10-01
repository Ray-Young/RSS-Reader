# This function is aimed to handle huge XML file and extract the information indo readable dataframe
import xml.etree.cElementTree as etree
import pandas as pd
import csv
import time

start_time = time.time()
# Store the result in question_dataframe.csv
with open('target.csv', 'w') as fp:
    # using iterparse to scan over 10GB, and most 40GB XML files
    # the algorithm is read the element and extract information, after this clean this element in memory
    # It will prevent memory overflow problem
    # First we store the result into a csv file, then we read the file and convert it to dataframe.
    # Because the data extracted is still very large, it will take lots memory space if we save it in a list
    for event, elem in etree.iterparse("resource.xml", events = ("start", "end")):
        if event == "end" and elem.tag == "row" and elem.get("PostTypeId") == "1":
            # Get the content from xml
            tag = elem.get("Tags")
            tag = tag[tag.find("<")+1:tag.find(">")]
            creation_date = elem.get("CreationDate")
            owner_user_id = elem.get("OwnerUserId")
            issue_id = elem.get("Id")
            if owner_user_id == "":
                owner_user_id = "None"
            data = [issue_id, creation_date, owner_user_id, tag]

            # Write the content to csv
            wr = csv.writer(fp, dialect='excel')
            wr.writerow(data)
            elem.clear()
fp.close

df = pd.read_csv('target.csv', names=["Id", "CreationDate", "OwnerUserId", "FirstTag"])
df.to_csv("target.csv")
print("--- %s seconds ---" % (time.time() - start_time))

# The total execution time for 10GB file is 305(s), with 8GB memory and i5 CPU, not SSD
