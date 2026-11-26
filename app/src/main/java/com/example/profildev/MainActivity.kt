package com.example.profildev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildev.ui.theme.ProfilDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfil(
                        nom = "Jeevons Eya-Jr Iq",
                        role = "Chef de projet IT",
                        description = "Passionné de technologie et d'astronomie. Je travaille comme chef de projet IT chez Laval Europe.",
                        competences = listOf("Gestion de projet", "Kotlin", "Git", "CI/CD", "Docker"),
                        projets = listOf(
                            Projet(
                                titre = "Profil développeur",
                                description = "Une fiche de présentation avec des composants réutilisables.",
                                technologies = "Kotlin, Jetpack Compose"
                            ),
                            Projet(
                                titre = "Product Explorer",
                                description = "Un écran de détail pour présenter un produit.",
                                technologies = "Kotlin, Jetpack Compose"
                            )
                        ),
                        contact = "linkedin.com/in/jeevons-e-3660a7297",
                        disponibilite = "Ouvert aux échanges sur de nouveaux projets",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Ecran principal : il place la fiche dans l'ecran
@Composable
fun EcranProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    projets: List<Projet>,
    contact: String,
    disponibilite: String,
    modifier: Modifier = Modifier
) {
    // padding externe : c'est l'ecran (le parent) qui decide de l'espace autour de la fiche
    FicheProfil(
        nom = nom,
        role = role,
        description = description,
        competences = competences,
        projets = projets,
        contact = contact,
        disponibilite = disponibilite,
        onContacterClick = {
            // pour l'instant on ne fait rien, le bouton signale juste le clic
        },
        onDisponibiliteClick = {
            // pareil, pas de logique ici pour le devoir
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

// La fiche complete avec toutes les infos
@Composable
fun FicheProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    projets: List<Projet>,
    contact: String,
    disponibilite: String,
    onContacterClick: () -> Unit,
    onDisponibiliteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        // Le contenu peut defiler pour garder le contact accessible sous les projets
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            EnTeteProfil(nom = nom, role = role)

            Text(
                text = description,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
            )

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleLarge
            )
            // LazyRow permet de faire defiler les competences horizontalement
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(competences) { competence ->
                    Competence(nom = competence)
                }
            }

            Text(
                text = "Projets",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
            )
            for (projet in projets) {
                CarteProjet(
                    projet = projet,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            InfoSecondaire(
                titre = "Contact",
                valeur = contact,
                modifier = Modifier.padding(top = 16.dp)
            )

            ZoneDisponibilite(
                texte = disponibilite,
                onClick = onDisponibiliteClick,
                modifier = Modifier.padding(top = 12.dp)
            )

            BoutonAction(
                texte = "Me contacter",
                onClick = onContacterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

// En-tete : avatar a gauche, nom et role a droite
@Composable
fun EnTeteProfil(nom: String, role: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AvatarProfil()

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = nom,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = role,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// Avatar generique : une image locale dans un fond rond
@Composable
fun AvatarProfil(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.avatar_profil),
            // L'avatar est decoratif : le nom est deja affiche juste a cote
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            modifier = Modifier.size(48.dp)
        )
    }
}

// Une competence affichee comme une petite etiquette
@Composable
fun Competence(nom: String, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = nom,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

// Une carte reutilisable pour chaque projet de la liste
@Composable
fun CarteProjet(projet: Projet, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = projet.titre,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = projet.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = projet.technologies,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

// Une info du type "Titre : valeur"
@Composable
fun InfoSecondaire(titre: String, valeur: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = titre, style = MaterialTheme.typography.labelLarge)
        Text(text = valeur)
    }
}

// Zone cliquable perso, donc il faut dire au lecteur d'ecran que c'est un bouton
@Composable
fun ZoneDisponibilite(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = Color(0xFFC8E6C9),
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "Voir ma disponibilité : $texte" }
    ) {
        Text(
            text = texte,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Bouton d'action reutilisable
@Composable
fun BoutonAction(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = texte)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun ProfilChefDeProjetPreview() {
    ProfilDevTheme {
        EcranProfil(
            nom = "Jeevons Eya-Jr Iq",
            role = "Chef de projet IT",
            description = "Passionné de technologie et d'astronomie. Je travaille comme chef de projet IT chez Laval Europe.",
            competences = listOf("Gestion de projet", "Kotlin", "Git", "CI/CD", "Docker"),
            projets = listOf(
                Projet(
                    titre = "Profil développeur",
                    description = "Une fiche de présentation avec des composants réutilisables.",
                    technologies = "Kotlin, Jetpack Compose"
                ),
                Projet(
                    titre = "Product Explorer",
                    description = "Un écran de détail pour présenter un produit.",
                    technologies = "Kotlin, Jetpack Compose"
                )
            ),
            contact = "linkedin.com/in/jeevons-e-3660a7297",
            disponibilite = "Ouvert aux échanges sur de nouveaux projets"
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun ProfilFullStackPreview() {
    ProfilDevTheme {
        EcranProfil(
            nom = "Camille Martin",
            role = "Développeuse full-stack",
            description = "Je travaille sur des applis web de A à Z, du back au front. J'aime quand le code est simple à relire.",
            competences = listOf("JavaScript", "React", "Node.js", "PostgreSQL"),
            projets = listOf(
                Projet(
                    titre = "Portfolio (exemple)",
                    description = "Un site pour présenter des projets web.",
                    technologies = "JavaScript, React"
                )
            ),
            contact = "camille.martin@exemple.fr",
            disponibilite = "Pas disponible en ce moment"
        )
    }
}
