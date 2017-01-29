//Yan R. Torres Roman      WEB Programming with Data Bases    Take-Home Test

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;
using System.Windows.Forms;

namespace TestExercise4
{
    class linkToPhp
    {
        public string serverRequest()
        {
            try
            {
                //Sends Webrequest to the page and gets the response.

                WebRequest webPageRequest = WebRequest.Create("http://ai-robotix.com/qx7/qx7_version.php");
                webPageRequest.Method = "GET";
                WebResponse webPageResponse = webPageRequest.GetResponse();

                //Creates a data steam from the web response and it's reader.

                Stream infoStream = webPageResponse.GetResponseStream();
                StreamReader reader = new StreamReader(infoStream);
                string pageResponse = reader.ReadToEnd();

                /*Read the stream from beginning to end and change it into a string,
                  so the function can return it. */

                return pageResponse;

                //Destructors

                reader.Close();
                infoStream.Close();
                webPageResponse.Close();
            }
            catch (WebException exception)
            {
                MessageBox.Show(exception.ToString());

                return "Failed to get server response.";
            }
        }
    }
}
