import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.diceroller.R
import com.example.diceroller.navigation.Screens

@Composable
fun YourBusinessCard(navController: NavController, resultShow: Int = 1, modifier: Modifier =Modifier.fillMaxSize().wrapContentSize(
    Alignment.Center)){

        Image(
            painter = painterResource(id = R.drawable.imagem_de_fundo_4),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.TopStart)) {
            Button(
                onClick = { navController.navigate(Screens.Roll.route + "/1") },
            ) {
                Text(text = stringResource(R.string.back), fontSize = 18.sp)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Greeting("Ricardo Sobral", "IT Technician & Student")

        }
}



@Composable
fun Greeting(name: String, mywork: String, modifier: Modifier = Modifier) {

    Spacer(modifier = Modifier.height(60.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {

        Box(
            modifier = Modifier
                .size(175.dp)
                .clip(CircleShape)
                .background(Color.Black), // Cor de fundo
            contentAlignment = Alignment.Center
        ) {
            // Imagem no círculo
            Image(
                painter = painterResource(id = R.drawable.img_6685__1_),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp) // Tamanho da imagem e do círculo
                    .clip(CircleShape) // Recorte da imagem
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = name,
            fontSize = 40.sp,
            modifier = modifier,
            textAlign = TextAlign.Center
        )

        Text(
            text = mywork,
            fontSize = 25.sp,
            modifier = modifier,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Contact("ricardo.sobral7@sapo.pt", 926445440, "ricardo.s02")
    }
}

@Composable
fun Contact(email: String, phonenumber: Int, igID: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), // Adiciona espaço ao redor dos elementos da função Contact
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //colocar todos estes paddings foi a maneira mais clean que arranjei para dispor o conteudo
        Image(
            painter = painterResource(id = R.drawable.qr_code__1_),
            contentDescription = "QR CODE",
            modifier = Modifier.size(155.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.email),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = email,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable._4915),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
            Text(
                text = phonenumber.toString(),
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.iglogo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "@$igID",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


