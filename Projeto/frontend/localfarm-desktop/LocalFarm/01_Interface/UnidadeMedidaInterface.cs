using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace LocalFarm._01_Interface
{
    public partial class UnidadeMedidaInterface : Form
    {
        public UnidadeMedidaInterface()
        {
            InitializeComponent();
        }

        private void UnidadeMedidaInterface_Load(object sender, EventArgs e)
        {
            tabControle.Appearance = TabAppearance.FlatButtons;
            tabControle.ItemSize = new Size(0, 1);
            tabControle.SizeMode = TabSizeMode.Fixed;

            foreach (TabPage tab in tabControle.TabPages)
            {
                tab.Text = "";
            }
        }

        private void btnFechar_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
            this.Close();
        }

        private void btnAdicionar_Click(object sender, EventArgs e)
        {
            tabControle.SelectedTab = tabManutencao;
        }

        private void btnSalvar_Click(object sender, EventArgs e)
        {
            tabControle.SelectedTab = tabGeral;
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            tabControle.SelectedTab = tabGeral;
        }

        private void brnEditar_Click(object sender, EventArgs e)
        {
            tabControle.SelectedTab = tabManutencao;
        }
    }
}
