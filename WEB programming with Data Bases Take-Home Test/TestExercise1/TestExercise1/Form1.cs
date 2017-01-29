//Yan R. Torres Roman      WEB Programming with Data Bases    Take-Home Test

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.IO;

namespace TestExercise1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
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
                string serverResponse = reader.ReadToEnd();

                /*Read the stream from beginning to end and change it into a string,
                so it it can change the text within the label to whatever the webpage
                responded.  */

                label1.Text = serverResponse;

                reader.Close();
                infoStream.Close();
                webPageResponse.Close();

                //Destructors 
            }
            catch(WebException exception)
            {
                MessageBox.Show(exception.ToString());

                //Shows whatever exception was caught.
            }
        }
    }
}
