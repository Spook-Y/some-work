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

namespace TestExercise2
{
    public partial class Form1 : Form
    {
        //Constructor

        linkToPhp phpLink = new linkToPhp();

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //Calls function within the class and sends web request and saves the response.

            phpLink.serverRequest();

            //Changes label1's text to the web response which has been saved in a variable within the function.

            label1.Text = phpLink.response;
        }
    }
}
