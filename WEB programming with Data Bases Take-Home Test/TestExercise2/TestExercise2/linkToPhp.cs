//Yan R. Torres Roman      WEB Programming with Data Bases    Take-Home Test

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Net;
using System.Windows.Forms;

namespace TestExercise2
{
    class linkToPhp
    {

        //Empty string that is used to save whatever is read from the stream.
        public string response = "";

        public void serverRequest()
        {
            try
            {

           //Sends Webrequest to the page and gets the response.

                WebRequest webPageRequest = WebRequest.Create("http://ai-robotix.com/qx7/qx7_version.php");
                webPageRequest.Method = "GET";
                WebResponse webPageResponse = webPageRequest.GetResponse();

           //Creates a data steam from the web response and it's reader

                Stream infoStream = webPageResponse.GetResponseStream();
                StreamReader reader = new StreamReader(infoStream);
                string pageResponse = reader.ReadToEnd();

           /*Read the stream from beginning to end and change it into a string,
             so it can be saved into the pre-declared public string to whatever the webpage
             responded.  */


                response = pageResponse;

            //Destructors

                reader.Close();
                infoStream.Close();
                webPageResponse.Close();
            }

            //Catches all web exceptions and shows a message box.
            catch(WebException exception)
            {
                
                MessageBox.Show(exception.ToString());
            }     
        }
    }
}
