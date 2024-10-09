package com.example.resistencias.scream

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Menu() {
    val context = LocalContext.current
    val opcionesColores = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val opcionesTolerancia = listOf("Dorado", "Plateado", "Ninguno")

    val valorColores = mapOf(
        "Negro" to 0,
        "Marrón" to 1,
        "Rojo" to 2,
        "Naranja" to 3,
        "Amarillo" to 4,
        "Verde" to 5,
        "Azul" to 6,
        "Violeta" to 7,
        "Gris" to 8,
        "Blanco" to 9
    )

    val toleranciaPorcentaje = mapOf(
        "Dorado" to 5,
        "Plateado" to 10,
        "Ninguno" to 20
    )

    var banda1 by remember { mutableStateOf(opcionesColores[0]) }
    var banda2 by remember { mutableStateOf(opcionesColores[0]) }
    var multiplicador by remember { mutableStateOf(opcionesColores[0]) }
    var tolerancia by remember { mutableStateOf(opcionesTolerancia[0]) }

    var expbanda1 by remember { mutableStateOf(false) }
    var expbanda2 by remember { mutableStateOf(false) }
    var expmultiplicador by remember { mutableStateOf(false) }
    var exptolerancia by remember { mutableStateOf(false) }

    val resistenciaValue = calculadoraResistencias(
        valorColores[banda1] ?: 0,
        valorColores[banda2] ?: 0,
        valorColores[multiplicador] ?: 0
    )
    val toleranciaValue = toleranciaPorcentaje[tolerancia] ?: 20

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Calculadora de Resistencias", style = MaterialTheme.typography.headlineSmall)

        // Banda 1
        ExposedDropdownMenuBox(
            expanded = expbanda1,
            onExpandedChange = { expbanda1 = !expbanda1 },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            TextField(
                value = banda1,
                onValueChange = {},
                readOnly = true,
                label = { Text("Banda 1") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expbanda1) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expbanda1,
                onDismissRequest = { expbanda1 = false }
            ) {
                opcionesColores.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            banda1 = option
                            expbanda1 = false
                            Toast.makeText(context, "Seleccionaste $option", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        // Banda 2
        ExposedDropdownMenuBox(
            expanded = expbanda2,
            onExpandedChange = { expbanda2 = !expbanda2 },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            TextField(
                value = banda2,
                onValueChange = {},
                readOnly = true,
                label = { Text("Banda 2") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expbanda2) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expbanda2,
                onDismissRequest = { expbanda2 = false }
            ) {
                opcionesColores.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            banda2 = option
                            expbanda2 = false
                            Toast.makeText(context, "Seleccionaste $option", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        // Multiplicador
        ExposedDropdownMenuBox(
            expanded = expmultiplicador,
            onExpandedChange = { expmultiplicador = !expmultiplicador },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            TextField(
                value = multiplicador,
                onValueChange = {},
                readOnly = true,
                label = { Text("Multiplicador") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expmultiplicador) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expmultiplicador,
                onDismissRequest = { expmultiplicador = false }
            ) {
                opcionesColores.forEach { color ->
                    DropdownMenuItem(
                        text = { Text(color) },
                        onClick = {
                            multiplicador = color
                            expmultiplicador = false
                        }
                    )
                }
            }
        }

        // Tolerancia
        ExposedDropdownMenuBox(
            expanded = exptolerancia,
            onExpandedChange = { exptolerancia = !exptolerancia },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            TextField(
                value = tolerancia,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tolerancia") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = exptolerancia) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = exptolerancia,
                onDismissRequest = { exptolerancia = false }
            ) {
                opcionesTolerancia.forEach { tol ->
                    DropdownMenuItem(
                        text = { Text(tol) },
                        onClick = {
                            tolerancia = tol
                            exptolerancia = false
                            Toast.makeText(context, "Seleccionaste $tol", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Mostrar el valor de la resistencia y la tolerancia
        Text(
            text = "Valor de la Resistencia: $resistenciaValue Ω",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Tolerancia: ±$toleranciaValue%",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
