using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LocalFarm._01_Interface
{
    public partial class MenuInicialInterface : Form
    {
        public MenuInicialInterface()
        {
            InitializeComponent();
        }

        private void MenuInicialInterface_Load(object sender, EventArgs e)
        {

            using (LoginInterface Form = new LoginInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }
        }

        private void btnLogout_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void btnUnidadeMedida_Click(object sender, EventArgs e)
        {

            using (UnidadeMedidaInterface Form = new UnidadeMedidaInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }

        }

        private void btnUsuario_Click(object sender, EventArgs e)
        {
            using (UsuarioInterface Form = new UsuarioInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }
        }

        private void btnCliente_Click(object sender, EventArgs e)
        {
            using (ClienteFornecedorInterface Form = new ClienteFornecedorInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }
        }

        private void btnProduto_Click(object sender, EventArgs e)
        {
            using (ProdutoInterface Form = new ProdutoInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }
        }

        private void btnMovimentoProduto_Click(object sender, EventArgs e)
        {
            using (MovimentoProdutoInterface Form = new MovimentoProdutoInterface())
            {
                this.Visible = false;
                Form.ShowDialog();
                // Aqui considerei que se o login for efetuado com sucesso, o DialogResult será OK
                if (Form.DialogResult != DialogResult.OK)
                {
                    Application.Exit();
                    return;
                }
                else
                {
                    this.Visible = true;
                }
            }
        }
    }
}
