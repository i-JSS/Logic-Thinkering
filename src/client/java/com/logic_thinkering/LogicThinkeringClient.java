package com.logic_thinkering;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import com.logic_thinkering.screen.MiningMachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class LogicThinkeringClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("logic_thinkering_book")
                    .executes(context -> {

                        StandardBookGuideBuilder builder = new StandardBookGuideBuilder();

                        builder.addPage("Introdução à Eletrônica Digital",
                                "A eletrônica digital é o ramo da eletrônica que trabalha com sinais representados por dois estados distintos: 0 (baixo) e 1 (alto). Esses estados correspondem a diferentes níveis de tensão elétrica, sendo a base da lógica binária usada em computadores, dispositivos móveis e sistemas embarcados.",
                                null);
                        builder.addPage("Circuitos Digitais",
                                "Os circuitos digitais são construídos a partir de portas lógicas, componentes fundamentais que processam e manipulam sinais binários. Cada porta lógica realiza uma operação específica, como combinar, inverter ou comparar os valores de entrada para produzir uma saída. \n O mod Logic Thinkering introduz no Minecraft novos componentes, como as portas básicas que formam a base de qualquer sistema digital: AND, OR e NOT.",
                                null);
                        builder.addPage("Porta AND (E)",
                                "A porta AND gera a saída \"1\" somente quando todas as entradas são \"1.\" Em qualquer outro caso, a saída será \"0.\" Essa porta é útil quando queremos validar múltiplas condições simultaneamente.",
                                "textures/client/and-table.png");
                        builder.addPage("Porta OR (OU)",
                                "A porta OR gera a saída \"1\" se pelo menos uma das entradas for \"1.\" Ela só produzirá \"0\" quando todas as entradas forem \"0.\" É frequentemente usada em situações onde qualquer condição verdadeira é suficiente.",
                                "textures/client/or-table.png");
                        builder.addPage("Porta NOT (NÃO)",
                                "A porta NOT inverte o valor da entrada. Se a entrada for \"0,\" a saída será \"1,\" e se a entrada for \"1,\" a saída será \"0.\" Essa porta é essencial para criar negações em circuitos lógicos.",
                                "textures/client/not-table.png");
                        builder.addPage("Logic Thinkering",
                                "Agora que você entende o funcionamento das portas lógicas AND, OR e NOT, pode começar a construir circuitos inteligentes no Minecraft utilizando os novos componentes adicionados pelo Logic Thinkering. Explore o potencial dos novos itens e experimente combinar diferentes portas lógicas para solucionar problemas de forma eficiente.",
                                null);

                        BookGuide book = builder.build();

                        MinecraftClient.getInstance().execute(() -> {
                            MinecraftClient.getInstance().setScreen(new CottonClientScreen(new BookGui(book)));
                        });
                        return 1;
                    })
            );

        });

        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        HandledScreens.register(com.logic_thinkering.screenhandler.ModScreenHandlers.MINING_MACHINE_SCREEN_HANDLER, MiningMachineScreen::new);
    }
}
