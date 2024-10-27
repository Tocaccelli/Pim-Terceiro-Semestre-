namespace LocalFarm._01_Interface
{
    partial class ProdutoInterface
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(ProdutoInterface));
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.panel1 = new System.Windows.Forms.Panel();
            this.label6 = new System.Windows.Forms.Label();
            this.btnFechar = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.tabControle = new System.Windows.Forms.TabControl();
            this.tabGeral = new System.Windows.Forms.TabPage();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.btnDesabilitar = new System.Windows.Forms.Button();
            this.brnEditar = new System.Windows.Forms.Button();
            this.btnAdicionar = new System.Windows.Forms.Button();
            this.tabManutencao = new System.Windows.Forms.TabPage();
            this.cboUnidadeDeMedida = new System.Windows.Forms.ComboBox();
            this.lblUnidadeDeMedida = new System.Windows.Forms.Label();
            this.cboTipoDeProduto = new System.Windows.Forms.ComboBox();
            this.lblTipoDeProduto = new System.Windows.Forms.Label();
            this.txtDescricao = new System.Windows.Forms.TextBox();
            this.txtCodigo = new System.Windows.Forms.TextBox();
            this.lblDescriçao = new System.Windows.Forms.Label();
            this.lblCodigo = new System.Windows.Forms.Label();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnSalvar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.panel1.SuspendLayout();
            this.tabControle.SuspendLayout();
            this.tabGeral.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.tabManutencao.SuspendLayout();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(3, 5);
            this.pictureBox1.Margin = new System.Windows.Forms.Padding(2);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(222, 122);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 4;
            this.pictureBox1.TabStop = false;
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.btnFechar);
            this.panel1.Controls.Add(this.pictureBox1);
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Margin = new System.Windows.Forms.Padding(2);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(1280, 129);
            this.panel1.TabIndex = 2;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.ForeColor = System.Drawing.Color.Transparent;
            this.label6.Location = new System.Drawing.Point(1164, 83);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(56, 13);
            this.label6.TabIndex = 9;
            this.label6.Text = "VOLTAR";
            // 
            // btnFechar
            // 
            this.btnFechar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.btnFechar.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnFechar.BackgroundImage")));
            this.btnFechar.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.btnFechar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnFechar.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFechar.ForeColor = System.Drawing.Color.White;
            this.btnFechar.Location = new System.Drawing.Point(1167, 37);
            this.btnFechar.Margin = new System.Windows.Forms.Padding(2);
            this.btnFechar.Name = "btnFechar";
            this.btnFechar.Size = new System.Drawing.Size(51, 44);
            this.btnFechar.TabIndex = 8;
            this.btnFechar.TextAlign = System.Drawing.ContentAlignment.BottomCenter;
            this.btnFechar.UseVisualStyleBackColor = false;
            this.btnFechar.Click += new System.EventHandler(this.btnFechar_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.label3.Location = new System.Drawing.Point(15, 150);
            this.label3.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(234, 24);
            this.label3.TabIndex = 8;
            this.label3.Text = "Manutenção de Produto";
            // 
            // tabControle
            // 
            this.tabControle.Controls.Add(this.tabGeral);
            this.tabControle.Controls.Add(this.tabManutencao);
            this.tabControle.Cursor = System.Windows.Forms.Cursors.Default;
            this.tabControle.Location = new System.Drawing.Point(19, 182);
            this.tabControle.Margin = new System.Windows.Forms.Padding(2);
            this.tabControle.Name = "tabControle";
            this.tabControle.SelectedIndex = 0;
            this.tabControle.Size = new System.Drawing.Size(1243, 447);
            this.tabControle.TabIndex = 10;
            // 
            // tabGeral
            // 
            this.tabGeral.BackColor = System.Drawing.Color.Tan;
            this.tabGeral.Controls.Add(this.dataGridView1);
            this.tabGeral.Controls.Add(this.btnDesabilitar);
            this.tabGeral.Controls.Add(this.brnEditar);
            this.tabGeral.Controls.Add(this.btnAdicionar);
            this.tabGeral.Cursor = System.Windows.Forms.Cursors.Default;
            this.tabGeral.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.tabGeral.Location = new System.Drawing.Point(4, 22);
            this.tabGeral.Margin = new System.Windows.Forms.Padding(2);
            this.tabGeral.Name = "tabGeral";
            this.tabGeral.Padding = new System.Windows.Forms.Padding(2);
            this.tabGeral.Size = new System.Drawing.Size(1235, 421);
            this.tabGeral.TabIndex = 0;
            this.tabGeral.Text = "Geral";
            // 
            // dataGridView1
            // 
            this.dataGridView1.BackgroundColor = System.Drawing.Color.AntiqueWhite;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(9, 49);
            this.dataGridView1.Margin = new System.Windows.Forms.Padding(2);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 62;
            this.dataGridView1.RowTemplate.Height = 28;
            this.dataGridView1.Size = new System.Drawing.Size(1208, 368);
            this.dataGridView1.TabIndex = 8;
            // 
            // btnDesabilitar
            // 
            this.btnDesabilitar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.btnDesabilitar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnDesabilitar.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnDesabilitar.ForeColor = System.Drawing.Color.White;
            this.btnDesabilitar.Location = new System.Drawing.Point(255, 8);
            this.btnDesabilitar.Margin = new System.Windows.Forms.Padding(2);
            this.btnDesabilitar.Name = "btnDesabilitar";
            this.btnDesabilitar.Size = new System.Drawing.Size(119, 31);
            this.btnDesabilitar.TabIndex = 7;
            this.btnDesabilitar.Text = "Desabilitar";
            this.btnDesabilitar.UseVisualStyleBackColor = false;
            // 
            // brnEditar
            // 
            this.brnEditar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.brnEditar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.brnEditar.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.brnEditar.ForeColor = System.Drawing.Color.White;
            this.brnEditar.Location = new System.Drawing.Point(132, 8);
            this.brnEditar.Margin = new System.Windows.Forms.Padding(2);
            this.brnEditar.Name = "brnEditar";
            this.brnEditar.Size = new System.Drawing.Size(119, 31);
            this.brnEditar.TabIndex = 6;
            this.brnEditar.Text = "Editar";
            this.brnEditar.UseVisualStyleBackColor = false;
            this.brnEditar.Click += new System.EventHandler(this.brnEditar_Click);
            // 
            // btnAdicionar
            // 
            this.btnAdicionar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.btnAdicionar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnAdicionar.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnAdicionar.ForeColor = System.Drawing.Color.White;
            this.btnAdicionar.Location = new System.Drawing.Point(9, 8);
            this.btnAdicionar.Margin = new System.Windows.Forms.Padding(2);
            this.btnAdicionar.Name = "btnAdicionar";
            this.btnAdicionar.Size = new System.Drawing.Size(119, 31);
            this.btnAdicionar.TabIndex = 5;
            this.btnAdicionar.Text = "Adicionar";
            this.btnAdicionar.UseVisualStyleBackColor = false;
            this.btnAdicionar.Click += new System.EventHandler(this.btnAdicionar_Click);
            // 
            // tabManutencao
            // 
            this.tabManutencao.BackColor = System.Drawing.Color.Tan;
            this.tabManutencao.Controls.Add(this.cboUnidadeDeMedida);
            this.tabManutencao.Controls.Add(this.lblUnidadeDeMedida);
            this.tabManutencao.Controls.Add(this.cboTipoDeProduto);
            this.tabManutencao.Controls.Add(this.lblTipoDeProduto);
            this.tabManutencao.Controls.Add(this.txtDescricao);
            this.tabManutencao.Controls.Add(this.txtCodigo);
            this.tabManutencao.Controls.Add(this.lblDescriçao);
            this.tabManutencao.Controls.Add(this.lblCodigo);
            this.tabManutencao.Controls.Add(this.btnCancelar);
            this.tabManutencao.Controls.Add(this.btnSalvar);
            this.tabManutencao.Location = new System.Drawing.Point(4, 22);
            this.tabManutencao.Margin = new System.Windows.Forms.Padding(2);
            this.tabManutencao.Name = "tabManutencao";
            this.tabManutencao.Padding = new System.Windows.Forms.Padding(2);
            this.tabManutencao.Size = new System.Drawing.Size(1235, 421);
            this.tabManutencao.TabIndex = 1;
            this.tabManutencao.Text = "Manutenção";
            // 
            // cboUnidadeDeMedida
            // 
            this.cboUnidadeDeMedida.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cboUnidadeDeMedida.FormattingEnabled = true;
            this.cboUnidadeDeMedida.Location = new System.Drawing.Point(247, 125);
            this.cboUnidadeDeMedida.Name = "cboUnidadeDeMedida";
            this.cboUnidadeDeMedida.Size = new System.Drawing.Size(187, 28);
            this.cboUnidadeDeMedida.TabIndex = 16;
            // 
            // lblUnidadeDeMedida
            // 
            this.lblUnidadeDeMedida.AutoSize = true;
            this.lblUnidadeDeMedida.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblUnidadeDeMedida.Location = new System.Drawing.Point(243, 102);
            this.lblUnidadeDeMedida.Name = "lblUnidadeDeMedida";
            this.lblUnidadeDeMedida.Size = new System.Drawing.Size(147, 20);
            this.lblUnidadeDeMedida.TabIndex = 15;
            this.lblUnidadeDeMedida.Text = "Unidade de Medida";
            // 
            // cboTipoDeProduto
            // 
            this.cboTipoDeProduto.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cboTipoDeProduto.FormattingEnabled = true;
            this.cboTipoDeProduto.Location = new System.Drawing.Point(30, 125);
            this.cboTipoDeProduto.Name = "cboTipoDeProduto";
            this.cboTipoDeProduto.Size = new System.Drawing.Size(187, 28);
            this.cboTipoDeProduto.TabIndex = 14;
            // 
            // lblTipoDeProduto
            // 
            this.lblTipoDeProduto.AutoSize = true;
            this.lblTipoDeProduto.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTipoDeProduto.Location = new System.Drawing.Point(26, 102);
            this.lblTipoDeProduto.Name = "lblTipoDeProduto";
            this.lblTipoDeProduto.Size = new System.Drawing.Size(121, 20);
            this.lblTipoDeProduto.TabIndex = 13;
            this.lblTipoDeProduto.Text = "Tipo de Produto";
            // 
            // txtDescricao
            // 
            this.txtDescricao.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtDescricao.Location = new System.Drawing.Point(247, 50);
            this.txtDescricao.Name = "txtDescricao";
            this.txtDescricao.Size = new System.Drawing.Size(484, 26);
            this.txtDescricao.TabIndex = 12;
            // 
            // txtCodigo
            // 
            this.txtCodigo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtCodigo.Location = new System.Drawing.Point(30, 50);
            this.txtCodigo.Name = "txtCodigo";
            this.txtCodigo.Size = new System.Drawing.Size(187, 26);
            this.txtCodigo.TabIndex = 11;
            // 
            // lblDescriçao
            // 
            this.lblDescriçao.AutoSize = true;
            this.lblDescriçao.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblDescriçao.Location = new System.Drawing.Point(243, 24);
            this.lblDescriçao.Name = "lblDescriçao";
            this.lblDescriçao.Size = new System.Drawing.Size(80, 20);
            this.lblDescriçao.TabIndex = 10;
            this.lblDescriçao.Text = "Descrição";
            // 
            // lblCodigo
            // 
            this.lblCodigo.AutoSize = true;
            this.lblCodigo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCodigo.Location = new System.Drawing.Point(26, 24);
            this.lblCodigo.Name = "lblCodigo";
            this.lblCodigo.Size = new System.Drawing.Size(59, 20);
            this.lblCodigo.TabIndex = 9;
            this.lblCodigo.Text = "Código";
            // 
            // btnCancelar
            // 
            this.btnCancelar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.btnCancelar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnCancelar.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnCancelar.ForeColor = System.Drawing.Color.White;
            this.btnCancelar.Location = new System.Drawing.Point(136, 376);
            this.btnCancelar.Margin = new System.Windows.Forms.Padding(2);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(119, 31);
            this.btnCancelar.TabIndex = 8;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = false;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // btnSalvar
            // 
            this.btnSalvar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(70)))), ((int)(((byte)(84)))), ((int)(((byte)(67)))));
            this.btnSalvar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSalvar.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSalvar.ForeColor = System.Drawing.Color.White;
            this.btnSalvar.Location = new System.Drawing.Point(13, 376);
            this.btnSalvar.Margin = new System.Windows.Forms.Padding(2);
            this.btnSalvar.Name = "btnSalvar";
            this.btnSalvar.Size = new System.Drawing.Size(119, 31);
            this.btnSalvar.TabIndex = 7;
            this.btnSalvar.Text = "Salvar";
            this.btnSalvar.UseVisualStyleBackColor = false;
            this.btnSalvar.Click += new System.EventHandler(this.btnSalvar_Click);
            // 
            // ProdutoInterface
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Tan;
            this.ClientSize = new System.Drawing.Size(1300, 653);
            this.Controls.Add(this.tabControle);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.panel1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "ProdutoInterface";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ProdutoInterface";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.ProdutoInterface_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.tabControle.ResumeLayout(false);
            this.tabGeral.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.tabManutencao.ResumeLayout(false);
            this.tabManutencao.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TabControl tabControle;
        private System.Windows.Forms.TabPage tabGeral;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button btnDesabilitar;
        private System.Windows.Forms.Button brnEditar;
        private System.Windows.Forms.Button btnAdicionar;
        protected System.Windows.Forms.TabPage tabManutencao;
        private System.Windows.Forms.Button btnCancelar;
        private System.Windows.Forms.Button btnSalvar;
        private System.Windows.Forms.TextBox txtDescricao;
        private System.Windows.Forms.TextBox txtCodigo;
        private System.Windows.Forms.Label lblDescriçao;
        private System.Windows.Forms.Label lblCodigo;
        private System.Windows.Forms.ComboBox cboUnidadeDeMedida;
        private System.Windows.Forms.Label lblUnidadeDeMedida;
        private System.Windows.Forms.ComboBox cboTipoDeProduto;
        private System.Windows.Forms.Label lblTipoDeProduto;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button btnFechar;
    }
}