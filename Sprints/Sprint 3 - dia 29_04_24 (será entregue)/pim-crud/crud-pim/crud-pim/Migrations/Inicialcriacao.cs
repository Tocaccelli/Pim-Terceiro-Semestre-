using crud_pim.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace crud_pim.Migrations
{
    [DbContext(typeof(Contexto))]
    [Migration("20211219050733_Inicial-criacao")]
    partial class Inicialcriacao
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
            #pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "6.0.1")
                .HasAnnotation("Relational:MaxIdentifierLength", 128);

            SqlServerModelBuilderExtensions.UseIdentityColumns(modelBuilder, 1L, 1);

            modelBuilder.Entity("crud_pim.Models", b =>
            {
                b.Property<int>("Id")
                    .ValueGeneratedOnAdd()
                    .HasColumnType("int")
                    .HasColumnName("Id");

                SqlServerPropertyBuilderExtensions.UseIdentityColumn(b.Property<int>("Id"), 1L, 1);

                b.Property<string>("Nome")
                    .IsRequired()
                    .HasColumnType("nvarchar(max)")
                    .HasColumnName("Nome");

                b.HasKey("Id");

                b.ToTable("Produto");
            });
                #pragma warning restore 612, 618
        }
    }
 }
